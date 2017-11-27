package com.wtransnet.training.kotlin.trainingkotlinapp.datasource

import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem

/**
* Created by davidmartin on 7/11/17.
*/
open class DataSource {

    open fun retrieveLegoList(): List<LegoItem>? {
        return (1..9).map { LegoItem(it, "Lego $it", "https://randomuser.me/api/portraits/lego/$it.jpg",
                getLegoType(it)) }
    }

    private fun getLegoType(id: Int): LegoItem.LegoType =
            if (id % 2 == 0) LegoItem.LegoType.WITH_HAT else LegoItem.LegoType.WITHOUT_HAT

    open fun retrieveLego(id: Int): LegoItem? = retrieveLegoList()?.first { it.id == id }
}