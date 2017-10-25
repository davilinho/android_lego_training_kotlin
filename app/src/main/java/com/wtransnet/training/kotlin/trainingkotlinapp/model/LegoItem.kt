package com.wtransnet.training.kotlin.trainingkotlinapp.model

/**
* Created by davidmartin on 23/10/17.
*/
class LegoItem(val id: Int, var text: String, val url: String, val type: LegoType) {
    enum class LegoType { ALL, WITH_HAT, WITHOUT_HAT }
}