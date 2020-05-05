package com.example.falabellatest.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataPojo (@SerializedName ("uf") var uf: DataDetailPojo,
                     @SerializedName("ivp") var ivp: DataDetailPojo,
                     @SerializedName("dolar") var dolar: DataDetailPojo,
                     @SerializedName("dolar_intercambio") var dolar_intercambio: DataDetailPojo,
                     @SerializedName  ("euro") var euro: DataDetailPojo,
                     @SerializedName  ("ipc") var ipc: DataDetailPojo,
                     @SerializedName  ("utm") var utm: DataDetailPojo,
                     @SerializedName  ("imacec") var imacec: DataDetailPojo,
                     @SerializedName  ("tpm") var tpm: DataDetailPojo,
                     @SerializedName  ("libra_cobre") var libra_cobre: DataDetailPojo,
                     @SerializedName  ("tasa_desempleo") var tasa_desempleo: DataDetailPojo,
                     @SerializedName  ("bitcoin") var bitcoin: DataDetailPojo
) : Serializable{
    fun getData() : List<DataDetailPojo> = listOf(uf, ivp,dolar,dolar_intercambio,
        euro,ipc,utm,imacec,tpm,
        libra_cobre,tasa_desempleo,bitcoin)
}