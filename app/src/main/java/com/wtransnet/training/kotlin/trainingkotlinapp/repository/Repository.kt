package com.wtransnet.training.kotlin.trainingkotlinapp.repository

import com.wtransnet.training.kotlin.trainingkotlinapp.datasource.DataSource
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.result.Error
import com.wtransnet.training.kotlin.trainingkotlinapp.result.Result
import io.reactivex.Observable
import javax.inject.Inject

/**
* Created by davidmartin on 24/10/17.
*/
class Repository @Inject constructor(private val dataSource: DataSource) {

    fun retrieveList(): Observable<Result<List<LegoItem>>> {
        val legoList = dataSource.retrieveLegoList()
        return if (!legoList?.isEmpty()!!) {
            Observable.defer { Observable.just(Result(legoList)) }
        } else {
            Observable.defer { Observable.just(Result<List<LegoItem>>(Error.notFound)) }
        }
    }

    fun retrieveDetail(id: Int): Observable<Result<LegoItem>> {
        val lego = dataSource.retrieveLego(id)
        return if (lego != null) {
            return Observable.defer { Observable.just(Result(lego)) }
        } else {
            Observable.defer { Observable.just(Result<LegoItem>(Error.notFound)) }
        }
    }
}