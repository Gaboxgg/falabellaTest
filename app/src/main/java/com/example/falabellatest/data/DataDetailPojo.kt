package com.example.falabellatest.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataDetailPojo (
    @SerializedName("codigo") var codigo:String="",
    @SerializedName("nombre") var nombre:String="",
    @SerializedName("unidad_medida") var unidad_medida:String="",
    @SerializedName("fecha") var fecha:String="",
    @SerializedName("valor") var valor:String=""
): Serializable