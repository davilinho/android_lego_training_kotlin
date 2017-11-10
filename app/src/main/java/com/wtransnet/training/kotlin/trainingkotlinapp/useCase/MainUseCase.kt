package com.wtransnet.training.kotlin.trainingkotlinapp.useCase

import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.repository.Repository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
* Created by davidmartin on 6/11/17.
*/
class MainUseCase @Inject constructor(private val repository: Repository): IUseCase<Unit, List<LegoItem>> {

    override fun execute(request: Unit?): Observable<List<LegoItem>> {
        return repository.retrieveList().observeOn(AndroidSchedulers.mainThread())
    }
}