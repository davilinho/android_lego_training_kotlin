package com.wtransnet.training.kotlin.trainingkotlinapp.repository

import com.wtransnet.training.kotlin.trainingkotlinapp.datasource.DataSource
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import io.reactivex.Observable
import javax.inject.Inject

/**
* Created by davidmartin on 24/10/17.
*/
class Repository @Inject constructor(private val dataSource: DataSource) {

    fun retrieveList(): Observable<List<LegoItem>> {
        return Observable.defer { Observable.just(dataSource.retrieveLegoList()) }
    }

    fun retrieveDetail(id: Int): Observable<LegoItem> {
        return Observable.defer { Observable.just(dataSource.retrieveLego(id)) }
    }
}