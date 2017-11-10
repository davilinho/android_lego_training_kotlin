package com.wtransnet.training.kotlin.trainingkotlinapp.presenter

import com.wtransnet.training.kotlin.trainingkotlinapp.jobqueue.Executor
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.useCase.MainUseCase
import com.wtransnet.training.kotlin.trainingkotlinapp.view.MainActivity
import javax.inject.Inject

/**
* Created by davidmartin on 23/10/17.
*/
class MainPresenter @Inject constructor(private val view: MainActivity, private val useCase: MainUseCase) {

    fun loadImages() {
        view.showLoading {
            Executor<Unit, List<LegoItem>>(view, Executor.Priority.HIGH)
                    .addJobInBackground(null, useCase, {
                        view.showOfflineMessage()
                    }, { response ->
                        view.showImages(response) {
                            view.hideLoading()
                        }
                    })
        }
    }

    fun filterImages(byType: LegoItem.LegoType) {
        view.showLoading {
            Executor<Unit, List<LegoItem>>(view, Executor.Priority.HIGH)
                    .addJobInBackground(null, useCase, {
                        view.showOfflineMessage()
                    }, { response ->
                        view.showImages(response.filter { it.type == byType } ) {
                            view.hideLoading()
                        }
                    })
        }
    }

    fun loadDetail(id: Int) {
        view.navigateToDetail(id)
    }

}