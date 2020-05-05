package com.example.falabellatest.data

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable

@Entity(tableName = "data_table")
data class Data(

    var code: String,
    var name: String,
    var unit: String,
    var date: String,
    var value: String

):Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}