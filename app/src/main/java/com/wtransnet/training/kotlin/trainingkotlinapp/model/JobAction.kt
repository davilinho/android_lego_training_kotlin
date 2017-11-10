package com.wtransnet.training.kotlin.trainingkotlinapp.model

import io.realm.RealmObject

/**
* Created by davidmartin on 9/11/17.
*/

open class JobAction(var data: String = "", var priority: Int = 0): RealmObject()