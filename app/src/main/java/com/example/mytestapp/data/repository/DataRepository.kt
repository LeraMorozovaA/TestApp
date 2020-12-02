package com.example.mytestapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytestapp.App.Companion.apiService
import com.example.mytestapp.R
import com.example.mytestapp.api.CompaniesApi
import com.example.mytestapp.data.State
import com.example.mytestapp.data.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class DataRepository(private val compositeDisposable: CompositeDisposable) {

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

    private val arrayList = ArrayList<AvailableDeliveryModel>()
    private val filtersArrayList = ArrayList<FilterModel>()

    fun getCompaniesList() {
        _state.value = State.LoggingState
        compositeDisposable.add(
                apiService.getCompaniesList(CompaniesApi.CITY_ID)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())

                        .subscribe(
                                { list ->
                                    _state.value = State.SucceededState
                                    _companiesList.postValue(list.toList())
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
                                    addFirstElement()
                                    arrayList.addAll(data.list)
                                    _deliveryDataList.postValue(arrayList)
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
                                    //добавить в бд
                                    addElement()
                                    filtersArrayList.addAll(data)
                                    _filtersList.postValue(filtersArrayList)
                                },
                                { error ->
                                    _state.value = State.ErrorState
                                    Log.e("DataSource", error.message.toString())
                                }
                        )
        )
    }

    fun getFiltersCompaniesList() {
        val list = ArrayList<AvailableCompaniesModel>()
        val element1 = AvailableCompaniesModel("Тільки активні","status")
        val element2 = AvailableCompaniesModel("З онлайн оплатою", "onlinePayment")
        list.add(element1)
        list.add(element2)
        _filtersCompaniesList.value = list
    }

    private fun addFirstElement(){
        val element = AvailableDeliveryModel(4, "Усі способи отримання")
        arrayList.add(0, element)
    }

    private fun addElement(){
        val element = FilterModel(15, "Усі категорії")
        filtersArrayList.add(0, element)
    }
}