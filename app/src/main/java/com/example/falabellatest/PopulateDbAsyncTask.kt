package com.example.falabellatest

import android.os.AsyncTask

class PopulateDbAsyncTask(db: DataDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val dataDao = db?.dataDao()

    override fun doInBackground(vararg p0: Unit?) {
        dataDao?.insert(Data("Title 1", "description 1","","","1"))
        dataDao?.insert(Data("Title 2", "description 2","","","2"))
        dataDao?.insert(Data("Title 3", "description 3","","","3"))
    }
}
