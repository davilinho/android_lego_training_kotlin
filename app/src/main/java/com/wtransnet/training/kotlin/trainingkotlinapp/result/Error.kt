package com.wtransnet.training.kotlin.trainingkotlinapp.result

/**
* Created by davidmartin on 14/11/17.
*/
data class Error(val code: Int, val message: String) {
    companion object {
        val notFound: Error = Error(100, "No se han encontrado resultados")
    }
}