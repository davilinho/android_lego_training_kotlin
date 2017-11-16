package com.wtransnet.training.kotlin.trainingkotlinapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.wtransnet.training.kotlin.trainingkotlinapp.R
import com.wtransnet.training.kotlin.trainingkotlinapp.app.CustomApplication
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem

/**
* Created by davidmartin on 23/10/17.
*/

val Activity.app: CustomApplication
    get() = application as CustomApplication

fun ImageView.loadUrl(url: String): Any =
        try { Glide.with(context).load(url).into(this) } catch (e: IllegalArgumentException) { }

fun Activity.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

const val DATA: String = "data"

inline fun <reified T: Activity> Activity.navigateTo(data: String) =
        startActivity(Intent(this, T::class.java).putExtra(DATA, data))

fun Context.mapLegoType(type: LegoItem.LegoType): String {
    return if (type.name == LegoItem.LegoType.WITH_HAT.name) getString(R.string.with_hat)
    else getString(R.string.without_hat)
}