package com.wtransnet.training.kotlin.trainingkotlinapp.presenter

import com.wtransnet.training.kotlin.trainingkotlinapp.jobqueue.Executor
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.result.Result
import com.wtransnet.training.kotlin.trainingkotlinapp.useCase.MainUseCase
import com.wtransnet.training.kotlin.trainingkotlinapp.view.MainActivity
import javax.inject.Inject

/**
* Created by davidmartin on 23/10/17.
*/
class MainPresenter @Inject constructor(val view: MainActivity, private val useCase: MainUseCase):
        Presenter(view, Executor.Priority.HIGH) {

    fun loadImages() {
        showLoading {
            executor.addJobInBackground(useCase, { showOfflineMessage() }, { response ->
                        when (response.type) {
                            Result.ResultType.SUCCESS -> response.value?.let { view.showImages(it) { hideLoading() }}
                            Result.ResultType.FAILURE -> response.error?.message?.let { showError(it) }
                        }
                    })
        }
    }

    fun filterImages(byType: LegoItem.LegoType) {
        showLoading {
            executor.addJobInBackground(useCase, { showOfflineMessage() }, { response ->
                        when (response.type) {
                            Result.ResultType.SUCCESS -> response.value?.let {
                                view.showImages(it.filter { it.type == byType }) { hideLoading() }
                            }
                            Result.ResultType.FAILURE -> response.error?.message?.let { showError(it) }
                        }
                    })
        }
    }

    fun loadDetail(id: Int) {
        view.navigateToDetail(id)
    }

    fun deleteLegoItem(item: LegoItem) {
        view.deleteItemFeedback(item)
    }
}