package com.example.mytestapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.data.State
import com.example.mytestapp.data.model.CompanyModel
import com.example.mytestapp.data.repository.DataRepository
import io.reactivex.disposables.CompositeDisposable

class ListViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val dataRepository = DataRepository(compositeDisposable)

    val state: LiveData<State>
    get() = dataRepository.state

    lateinit var companiesList: LiveData<List<CompanyModel>>

    fun getCompaniesList() {
        dataRepository.getCompaniesList()
        companiesList = dataRepository.companiesList
    }

    fun onFiltersLayoutClicked() {

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}