package com.example.mytestapp.ui.filters

import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.data.model.AvailableCompaniesModel
import com.example.mytestapp.data.model.AvailableDeliveryModel
import com.example.mytestapp.data.model.DeliveryModel
import com.example.mytestapp.data.model.FilterModel
import com.example.mytestapp.data.repository.DataRepository
import com.example.mytestapp.ui.interfaces.ISelected
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class FiltersViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val dataRepository = DataRepository(compositeDisposable)
    private val ty = 4

    lateinit var deliveryList: LiveData<List<AvailableDeliveryModel>>
    lateinit var filtersCompaniesList: LiveData<List<AvailableCompaniesModel>>
    lateinit var filtersList: LiveData<List<FilterModel>>
    var deliveryClick = MutableLiveData(4)
    //var  countOfCompanies: LiveData<Int>


    fun getAvailableDeliveryList() {
        dataRepository.getAvailableListFromDB()
        deliveryList = dataRepository.deliveryDataList
    }

    fun getFiltersCompaniesList(){
        dataRepository.getFiltersCompaniesList()
        filtersCompaniesList = dataRepository.filtersCompaniesList
    }

    fun getFiltersList(){
        dataRepository.getFiltersListFromDB()
        filtersList = dataRepository.filtersList
    }

    fun getDeliveryClick(selectedItem: Int){
        deliveryClick.value = selectedItem
    }

    fun getFiltersClick(selectedItem: Int){
       // deliveryClick.set(selectedItem)
    }

    fun getCompanyClick(selectedItem: Int){
       // deliveryClick.set(selectedItem)
    }

//    fun get(){
//        dataRepository.getResults()
//        countOfCompanies = dataRepository.countOfCompanies
//    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}