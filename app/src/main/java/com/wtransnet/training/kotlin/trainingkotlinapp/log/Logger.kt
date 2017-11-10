package com.wtransnet.training.kotlin.trainingkotlinapp.log

import android.util.Log
import hugo.weaving.DebugLog

/**
* Created by davidmartin on 3/11/17.
*/
class Logger private constructor() {

    private object Singleton { val INSTANCE = Logger() }

    companion object {
        val instance: Logger by lazy { Singleton.INSTANCE }
    }

    @DebugLog
    inline fun <reified T> debug(message: String) {
        Log.d(T::class.java.simpleName, message)
    }

    @DebugLog
    inline fun <reified T> info(message: String) {
        Log.i(T::class.java.simpleName, message)
    }

    @DebugLog
    inline fun <reified T> warning(message: String) {
        Log.w(T::class.java.simpleName, message)
    }

    @DebugLog
    inline fun <reified T> warning(message: String, t: Throwable) {
        Log.w(T::class.java.simpleName, message, t)
    }

    @DebugLog
    inline fun <reified T> error(message: String) {
        Log.e(T::class.java.simpleName, message)
    }

    @DebugLog
    inline fun <reified T> error(message: String, t: Throwable) {
        Log.e(T::class.java.simpleName, message, t)
    }
}