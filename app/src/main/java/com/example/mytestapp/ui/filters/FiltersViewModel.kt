package com.example.mytestapp.ui.filters

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.data.State
import com.example.mytestapp.data.model.AvailableCompaniesModel
import com.example.mytestapp.data.model.AvailableDeliveryModel
import com.example.mytestapp.data.model.DeliveryModel
import com.example.mytestapp.data.model.FilterModel
import com.example.mytestapp.data.repository.DataRepository
import com.example.mytestapp.ui.interfaces.ISelected
import io.reactivex.disposables.CompositeDisposable

class FiltersViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val dataRepository = DataRepository(compositeDisposable)
    private val listOfFilters = ArrayList<Pair<Int, ISelected>>()

    lateinit var deliveryList: LiveData<List<AvailableDeliveryModel>>
    lateinit var filtersCompaniesList: LiveData<List<AvailableCompaniesModel>>
    lateinit var filtersList: LiveData<List<FilterModel>>
    lateinit var  countOfCompanies: LiveData<Int>


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

    fun getData(selectedItem: ISelected, type: Int){
        val pair: Pair<Int, ISelected> = type to selectedItem
        listOfFilters.add(pair)
        get()
    }

    fun get(){
        dataRepository.getResults()
        countOfCompanies = dataRepository.countOfCompanies
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}