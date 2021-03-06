package com.example.falabellatest.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.falabellatest.data.Data

@Dao
interface DataDao {

    @Insert
    fun insert(data: Data)

    @Query("DELETE FROM data_table")
    fun deleteAllData()

    @Query("SELECT * FROM data_table ")
    fun getAllData(): LiveData<List<Data>>

    @Query("SELECT * FROM data_table WHERE code=:code")
    fun getDataByCode(code:String): Data

    @Query("SELECT * FROM data_table  WHERE code LIKE :filter")
    fun getDataByFilter(filter: String): LiveData<List<Data>>

}