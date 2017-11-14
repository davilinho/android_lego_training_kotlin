package com.wtransnet.training.kotlin.trainingkotlinapp.presenter

import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.result.Result
import com.wtransnet.training.kotlin.trainingkotlinapp.useCase.DetailUseCase
import com.wtransnet.training.kotlin.trainingkotlinapp.view.DetailActivity
import javax.inject.Inject

/**
* Created by davidmartin on 24/10/17.
*/
class DetailPresenter @Inject constructor(val view: DetailActivity, private val useCase: DetailUseCase):
        Presenter(view) {

    fun loadDetail(id: Int) {
        executor.addJobInBackground(id, useCase, { showOfflineMessage()
                }, { response ->
                    when (response.type) {
                        Result.ResultType.SUCCESS -> response.value?.let { view.showDetail(it) }
                        Result.ResultType.FAILURE -> response.error?.message?.let { showError(it) }
                    }
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