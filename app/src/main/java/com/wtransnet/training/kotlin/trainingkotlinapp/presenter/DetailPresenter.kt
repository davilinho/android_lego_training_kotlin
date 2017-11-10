package com.wtransnet.training.kotlin.trainingkotlinapp.presenter

import com.wtransnet.training.kotlin.trainingkotlinapp.jobqueue.Executor
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.useCase.DetailUseCase
import com.wtransnet.training.kotlin.trainingkotlinapp.view.DetailActivity
import javax.inject.Inject

/**
* Created by davidmartin on 24/10/17.
*/
class DetailPresenter@Inject constructor(private val view: DetailActivity, private val useCase: DetailUseCase) {

    fun loadDetail(id: Int) {
        Executor<Int, LegoItem>(view, Executor.Priority.HIGH)
                .addJobInBackground(id, useCase, {
                    view.showOfflineMessage()
                }, { response ->
                    view.showDetail(response)
                })
    }

    fun openInfoDetail(item: LegoItem) {
        item.let {
            with (item) {
                view.showInfoDetail("$id\n$text\n$url")
            }
        }
    }
}