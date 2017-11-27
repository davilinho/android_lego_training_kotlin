package com.wtransnet.training.kotlin.trainingkotlinapp.useCase

import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.repository.Repository
import com.wtransnet.training.kotlin.trainingkotlinapp.result.Result
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
* Created by davidmartin on 10/11/17.
*/
open class DetailUseCase @Inject constructor(private val repository: Repository): IUseCase<Int, LegoItem> {

    override fun execute(request: Int?): Observable<Result<LegoItem>> =
            repository.retrieveDetail(request!!).observeOn(AndroidSchedulers.mainThread())
}