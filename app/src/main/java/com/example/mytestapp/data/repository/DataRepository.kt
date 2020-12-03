package com.example.mytestapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytestapp.App
import com.example.mytestapp.App.Companion.apiService
import com.example.mytestapp.api.CompaniesApi
import com.example.mytestapp.data.State
import com.example.mytestapp.data.model.*
import com.example.mytestapp.util.getResults
import com.example.mytestapp.util.saveToSharedPref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class DataRepository(private val compositeDisposable: CompositeDisposable) {

    private val database = App.database.companiesModelDAO()

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    private val _companiesList = MutableLiveData<List<CompanyModel>>()

    val companiesList: LiveData<List<CompanyModel>>
        get() = _companiesList

    private val _deliveryDataList =
        MutableLiveData<List<AvailableDeliveryModel>>()

    val deliveryDataList: LiveData<List<AvailableDeliveryModel>>
        get() = _deliveryDataList

    private val _filtersList = MutableLiveData<List<FilterModel>>()

    val filtersList: LiveData<List<FilterModel>>
        get() = _filtersList

    private val _filtersCompaniesList = MutableLiveData<List<AvailableCompaniesModel>>()

    val filtersCompaniesList: LiveData<List<AvailableCompaniesModel>>
        get() = _filtersCompaniesList

    private val _list = MutableLiveData<List<CompanyModel>>()

//    private val _countOfCompanies = MutableLiveData<Int>()
//
//    val countOfCompanies: LiveData<Int>
//        get() = _countOfCompanies


    fun getCompaniesList() {
        _state.value = State.LoggingState
        compositeDisposable.add(
            apiService.getCompaniesList(CompaniesApi.CITY_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { list ->
                        _state.value = State.SucceededState
                        _companiesList.value = list
                        insertCompaniesIntoDB(list)
                        getCompaniesListFromDB()
                    },
                    { error ->
                        _state.value = State.ErrorState
                        Log.e("DataSource", error.message.toString())
                    }
                )
        )
    }

    fun getAvailableDeliveryData() {
        _state.value = State.LoggingState
        compositeDisposable.add(
            apiService.getAvailableDeliveryData(CompaniesApi.CITY_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { data ->
                        _state.value = State.SucceededState
                        insertIntoDB(addFirstElement(data.list))

                    },
                    { error ->
                        _state.value = State.ErrorState
                        Log.e("DataSource", error.message.toString())
                    }
                )
        )
    }

    fun getFiltersList() {
        _state.value = State.LoggingState
        compositeDisposable.add(
            apiService.getFiltersList(CompaniesApi.CITY_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { data ->
                        _state.value = State.SucceededState
                        insertElementsIntoDB(data)
                    },
                    { error ->
                        _state.value = State.ErrorState
                        Log.e("DataSource", error.message.toString())
                    }
                )
        )
    }

    fun getFiltersListFromDB(){
        compositeDisposable.add(database.getFilterModel()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                _filtersList.value = list
            })
    }

    fun getAvailableListFromDB(){
        compositeDisposable.add(database.getDeliveryModel()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { model ->
                _deliveryDataList.value = model.list
            }
        )
    }

    fun getCompaniesListFromDB() {
        compositeDisposable.add(database.getCompaniesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                data.getResults(4, 0)
            }
        )
    }

//        val observableList: Observable<CompanyModel> = Observable.fromIterable(oldList)
//        val subscribe = observableList.filter { it.availableDeliveryTypes!!.contains(element) }
//            .toList()
//            .observeOn(Schedulers.computation())
//            .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe( {
//                _companiesList.value = it
//                _countOfCompanies.value = it.size
//            }, {
//                Log.e("DataSource", it.message.toString())
//        })
//        compositeDisposable.add(subscribe)


    fun getFiltersCompaniesList() {
        val list = ArrayList<AvailableCompaniesModel>()
        val element1 = AvailableCompaniesModel("Тільки активні", "status")
        val element2 = AvailableCompaniesModel("З онлайн оплатою", "onlinePayment")
        list.add(element1)
        list.add(element2)
        _filtersCompaniesList.value = list
    }

    private fun insertIntoDB(model: DeliveryModel){
        compositeDisposable.add(
            database.insertDeliveryModel(model)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ()
        )
    }

    private fun addFirstElement(list: List<AvailableDeliveryModel>): DeliveryModel{
        val arrayList = ArrayList<AvailableDeliveryModel>()
        val element = AvailableDeliveryModel(4, "Усі способи отримання")
        arrayList.add(0, element)
        arrayList.addAll(list)
        return DeliveryModel((arrayList))
    }

    private fun insertElementsIntoDB(list: List<FilterModel>) {
        val filtersArrayList = ArrayList<FilterModel>()
        val element = FilterModel(0, "Усі категорії")
        filtersArrayList.add(0, element)
        filtersArrayList.addAll(list)
        for (i in filtersArrayList){
            database.insertFilterModel(i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }

    private fun insertCompaniesIntoDB(list: List<CompanyModel>) {
        for (i in list){
            database.insertCompaniesModel(i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }
}