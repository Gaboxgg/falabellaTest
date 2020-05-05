package com.example.falabellatest

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data_table")
data class Data(

    var code: String,
    var name: String,
    var unit: String,
    var date: String,
    var value: String

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}