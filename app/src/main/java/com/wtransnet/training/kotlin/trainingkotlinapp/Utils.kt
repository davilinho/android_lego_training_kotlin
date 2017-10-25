package com.wtransnet.training.kotlin.trainingkotlinapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import org.jetbrains.anko.contentView

/**
* Created by davidmartin on 23/10/17.
*/

val Activity.app: CustomApplication
    get() = application as CustomApplication

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun Activity.snack(message: String) {
    Snackbar.make(contentView!!, message, Snackbar.LENGTH_SHORT).show()
}

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

const val DATA: String = "data"

inline fun <reified T: Activity> Activity.navigateTo(data: String) {
    startActivity(Intent(this, T::class.java).putExtra(DATA, data))
}

fun Context.mapLegoType(type: LegoItem.LegoType): String {
    return if (type.name == LegoItem.LegoType.WITH_HAT.name) getString(R.string.with_hat)
    else getString(R.string.without_hat)
}