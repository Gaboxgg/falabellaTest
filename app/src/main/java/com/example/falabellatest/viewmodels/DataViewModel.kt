package com.example.falabellatest.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.falabellatest.data.Data
import com.example.falabellatest.data.DataRepository

class DataViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: DataRepository =
        DataRepository(application)
    private var allData: LiveData<List<Data>> = repository.getAllData()

    fun insert(data: Data) {
        repository.insert(data)
    }

    fun deleteAllData() {
        repository.deleteAllData()
    }

    fun getDataByCode(code:String): Data {
        return repository.getDataByCode(code)
    }

    fun getDataByFilter(filter:String):LiveData<List<Data>> {
        return repository.getDataByFilter(filter)
    }

    fun getAllData(): LiveData<List<Data>> {
        return allData
    }
}