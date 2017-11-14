package com.wtransnet.training.kotlin.trainingkotlinapp.result

/**
* Created by davidmartin on 14/11/17.
*/

class Result<T> {

    enum class ResultType { SUCCESS, FAILURE }

    var value: T? = null
    var error: Error? = null
    var type: ResultType

    constructor(value: T) {
        this.value = value
        this.type = ResultType.SUCCESS
    }

    constructor(error: Error) {
        this.error = error
        this.type = ResultType.FAILURE
    }
}
