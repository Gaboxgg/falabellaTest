package com.example.falabellatest

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {

    @Insert
    fun insert(data: Data)

    @Query("DELETE FROM data_table")
    fun deleteAllData()

    @Query("SELECT * FROM data_table ")
    fun getAllData(): LiveData<List<Data>>

}