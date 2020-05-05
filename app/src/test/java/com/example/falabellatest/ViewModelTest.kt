package com.example.falabellatest

import com.example.falabellatest.data.Data
import com.example.falabellatest.viewmodels.DataViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ViewModelTest {

    private lateinit var dataViewModel: DataViewModel

    @Before
    fun setup() {
        dataViewModel = Mockito.mock(DataViewModel::class.java)
    }

    @Test
    fun whenInserInitialData() {
        dataViewModel.insert( Data(
            "code",
            "name",
            "unit",
            "date",
            "value"
        ))
        Mockito.verify(dataViewModel).insert( Data(
            "code",
            "name",
            "unit",
            "date",
            "value"
        ))
    }
    @Test
    fun whenGetInitialData() {
        dataViewModel.getAllData()
        Mockito.verify(dataViewModel).getAllData()
    }

    @Test
    fun whenGetDataByFilter() {
        dataViewModel.getDataByFilter("code")
        Mockito.verify(dataViewModel).getDataByFilter("code")
    }

    @Test
    fun whenDeleteAllData() {
        dataViewModel.deleteAllData()
        Mockito.verify(dataViewModel).deleteAllData()
    }

}