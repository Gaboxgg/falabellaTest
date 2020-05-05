package com.example.falabellatest

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class DataRepository(application: Application) {

    private var dataDao: DataDao

    private var allData: LiveData<List<Data>>

    init {
        val database: DataDatabase  = DataDatabase.DatabaseProvider.getDataBase(
            application.applicationContext
        )!!
        dataDao = database.dataDao()
        allData = dataDao.getAllData()
    }

    fun insert(data: Data) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(dataDao).execute(data)
    }

    fun deleteAllData() {
        val deleteAllNotesAsyncTask = DeleteAllDataAsyncTask(
            dataDao
        ).execute()
    }

    fun getAllNotes(): LiveData<List<Data>> {
        return allData
    }

    private class InsertNoteAsyncTask(dataDao: DataDao) : AsyncTask<Data, Unit, Unit>() {
        val dataDao = dataDao

        override fun doInBackground(vararg p0: Data?) {
            dataDao.insert(p0[0]!!)
        }
    }


    private class DeleteAllDataAsyncTask(val dataDao: DataDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            dataDao.deleteAllData()
        }
    }

}