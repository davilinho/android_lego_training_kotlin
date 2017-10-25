package com.wtransnet.training.kotlin.trainingkotlinapp.presenter

import com.wtransnet.training.kotlin.trainingkotlinapp.Repository
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.view.DetailActivity

/**
* Created by davidmartin on 24/10/17.
*/
class DetailPresenter(val view: DetailActivity) {

    fun loadDetail(id: Int) {
        Repository.retrieveList { response ->
            view.showDetail(response.first { it.id == id })
        }
    }

    fun openInfoDetail(item: LegoItem) {
        item.let {
            with (item) {
                view.showInfoDetail("$id\n$text\n$url")
            }
        }
    }
}