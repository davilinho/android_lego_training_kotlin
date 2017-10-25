package com.wtransnet.training.kotlin.trainingkotlinapp

import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
* Created by davidmartin on 24/10/17.
*/
object Repository {

    fun retrieveList(callback: (List<LegoItem>) -> Unit) {
        doAsync {
            val list = retrieveLegoList()
            uiThread { callback(list) }
        }
    }

    private fun retrieveLegoList(): List<LegoItem> {
        return (1..9).map { LegoItem(it, "Lego $it", "https://randomuser.me/api/portraits/lego/$it.jpg",
                getLegoType(it)) }
    }

    private fun getLegoType(id: Int): LegoItem.LegoType {
        return if (id % 2 == 0) LegoItem.LegoType.WITH_HAT else LegoItem.LegoType.WITHOUT_HAT
    }
}