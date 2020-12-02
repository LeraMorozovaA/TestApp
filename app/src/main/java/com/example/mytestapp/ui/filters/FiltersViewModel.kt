package com.example.mytestapp.ui.filters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.data.State
import com.example.mytestapp.data.model.AvailableCompaniesModel
import com.example.mytestapp.data.model.AvailableDeliveryModel
import com.example.mytestapp.data.model.DeliveryModel
import com.example.mytestapp.data.model.FilterModel
import com.example.mytestapp.data.repository.DataRepository
import io.reactivex.disposables.CompositeDisposable

class FiltersViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val dataRepository = DataRepository(compositeDisposable)

    val state: LiveData<State>
        get() = dataRepository.state

    lateinit var deliveryList: LiveData<List<AvailableDeliveryModel>>
    lateinit var filtersCompaniesList: LiveData<List<AvailableCompaniesModel>>
    lateinit var filtersList: LiveData<List<FilterModel>>

    fun getAvailableDeliveryList() {
        dataRepository.getAvailableDeliveryData()
        deliveryList = dataRepository.deliveryDataList
    }

    fun getFiltersCompaniesList(){
        dataRepository.getFiltersCompaniesList()
        filtersCompaniesList = dataRepository.filtersCompaniesList
    }

    fun getFiltersList(){
        dataRepository.getFiltersList()
        filtersList = dataRepository.filtersList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}