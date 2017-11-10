package com.wtransnet.training.kotlin.trainingkotlinapp.useCase

import io.reactivex.Observable

/**
* Created by davidmartin on 6/11/17.
*/

interface IUseCase<in T1, T2> {
    fun execute(request: T1?): Observable<T2>
}