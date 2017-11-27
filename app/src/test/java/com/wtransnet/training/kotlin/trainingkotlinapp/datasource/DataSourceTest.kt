package com.wtransnet.training.kotlin.trainingkotlinapp.datasource

import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import org.junit.Assert
import org.junit.Test

/**
* Created by davidmartin on 23/11/17.
*/
class DataSourceTest {

    private val dataSource = DataSource()

    @Test
    fun retrieveList() {
        val items = dataSource.retrieveLegoList()
        Assert.assertEquals(items?.size, 9)
    }

    @Test
    fun retrieveLego() {
        val item = dataSource.retrieveLego(1)
        Assert.assertEquals(item?.id, 1)
    }

    @Test
    fun getLegoTypeWithHat() {
        val item = dataSource.retrieveLego(2)
        Assert.assertEquals(item?.type, LegoItem.LegoType.WITH_HAT)
    }

    @Test
    fun getLegoTypeWithoutHat() {
        val item = dataSource.retrieveLego(1)
        Assert.assertEquals(item?.type, LegoItem.LegoType.WITHOUT_HAT)
    }
}