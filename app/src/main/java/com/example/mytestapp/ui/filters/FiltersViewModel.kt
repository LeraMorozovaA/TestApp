package com.example.mytestapp.ui.filters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.data.model.AvailableCompaniesModel
import com.example.mytestapp.data.model.AvailableDeliveryModel
import com.example.mytestapp.data.model.CompanyModel
import com.example.mytestapp.data.model.FilterModel
import com.example.mytestapp.data.repository.DataRepository
import io.reactivex.disposables.CompositeDisposable

class FiltersViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val dataRepository = DataRepository(compositeDisposable)

    lateinit var deliveryList: LiveData<List<AvailableDeliveryModel>>
    lateinit var filtersCompaniesList: LiveData<List<AvailableCompaniesModel>>
    lateinit var filtersList: LiveData<List<FilterModel>>
    lateinit var filteredList: LiveData<List<CompanyModel>>

    var deliveryClick: MutableLiveData<Int> = MutableLiveData(4)
    var companyClick: MutableLiveData<ArrayList<String>> = MutableLiveData(ArrayList())
    var filtersClick: MutableLiveData<Int> = MutableLiveData(0)

    fun getAvailableDeliveryList() {
        dataRepository.getAvailableListFromDB()
        deliveryList = dataRepository.deliveryDataList
    }

    fun getFiltersCompaniesList() {
        dataRepository.getFiltersCompaniesList()
        filtersCompaniesList = dataRepository.filtersCompaniesList
    }

    fun getFiltersList() {
        dataRepository.getFiltersListFromDB()
        filtersList = dataRepository.filtersList
    }

    fun getDeliveryClick(selectedItem: Int) {
        deliveryClick.value = selectedItem
        getResults()
    }

    fun getFiltersClick(selectedItem: Int) {
        filtersClick.value = selectedItem
        getResults()
    }

    fun getCompanyClick(selectedItem: String) {
        companyClick.value!!.add(selectedItem)
        getResults()
    }

    fun unCheckedPosition(selectedItem: String){
        companyClick.value!!.remove(selectedItem)
        getResults()
    }

    fun getCompaniesListSize(){
        dataRepository.getCompaniesListFromDB(4,0, emptyList())
        filteredList = dataRepository.filteredList
    }

    private fun getResults() {
        dataRepository.getCompaniesListFromDB(
            deliveryClick.value!!,
            filtersClick.value!!,
            companyClick.value!!
        )
        filteredList = dataRepository.filteredList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}