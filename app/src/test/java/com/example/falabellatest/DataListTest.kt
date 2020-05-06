package com.example.falabellatest

import com.example.falabellatest.fragments.DataListFragment
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class DataListTest {
    private lateinit var dataListFragment: DataListFragment

    @Before
    fun setup() {
        dataListFragment = Mockito.mock(DataListFragment::class.java)
    }

    @Test
    fun whenSearchData() {
        dataListFragment.searchData()
        Mockito.verify(dataListFragment).searchData()
    }
}