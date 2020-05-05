package com.example.falabellatest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class DataViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: DataRepository =
        DataRepository(application)
    private var allData: LiveData<List<Data>> = repository.getAllNotes()

    fun insert(data: Data) {
        repository.insert(data)
    }

    fun deleteAllData() {
        repository.deleteAllData()
    }

    fun getAllData(): LiveData<List<Data>> {
        return allData
    }
}