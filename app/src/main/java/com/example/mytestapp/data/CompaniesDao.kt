package com.example.mytestapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mytestapp.data.model.CompanyModel
import com.example.mytestapp.data.model.DeliveryModel
import com.example.mytestapp.data.model.FilterModel
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface CompaniesDao {

    @Query("SELECT * FROM delivery_model")
    fun getDeliveryModel(): Maybe<DeliveryModel>

    @Query("SELECT * FROM filter_model")
    fun getFilterModel(): Maybe<List<FilterModel>>

    @Query("SELECT * FROM company_model")
    fun getCompaniesList(): Maybe<List<CompanyModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = DeliveryModel::class)
    fun insertDeliveryModel(model: DeliveryModel): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = FilterModel::class)
    fun insertFilterModel(model: FilterModel): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = CompanyModel::class)
    fun insertCompaniesModel(model: CompanyModel): Completable

}