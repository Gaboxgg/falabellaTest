package com.example.falabellatest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Data::class], version = 1)
abstract class DataDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao

    private var instance: DataDatabase? = null

    fun getInstance(context: Context): DataDatabase? {
        if (instance == null) {
            synchronized(DataDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataDatabase::class.java, "data_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
        }
        return instance
    }

    object DatabaseProvider {
        val DB_NAME : String = "data_database"
        private  var INSTANCE : DataDatabase? = null

        fun getDataBase(context: Context): DataDatabase? {
            if (INSTANCE == null) {
                synchronized(DataDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataDatabase::class.java, "data_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }}

            return INSTANCE
        }
        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(INSTANCE)
                    .execute()
            }
        }
    }

    private val roomCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            PopulateDbAsyncTask(instance)
                .execute()
        }
    }


}
