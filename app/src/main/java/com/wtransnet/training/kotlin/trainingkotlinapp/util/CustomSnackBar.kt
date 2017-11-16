package com.wtransnet.training.kotlin.trainingkotlinapp.util

import android.app.Activity
import android.support.design.widget.Snackbar
import org.jetbrains.anko.contentView

/**
* Created by davidmartin on 14/11/17.
*/
fun Activity.snack(message: String) = Snackbar.make(contentView!!, message, Snackbar.LENGTH_SHORT).show()

/** CANCEL ACTION */
fun Activity.cancelSnack(message: String, callback: () -> CancelSnackCallback, cancelAction: () -> Unit) =
        Snackbar.make(contentView!!, message, Snackbar.LENGTH_LONG)
                .addCallback(callback())
                .setAction("Cancelar") { cancelAction() }
                .show()

class CancelSnackCallback(private val callback: () -> Unit): Snackbar.Callback() {
    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
        super.onDismissed(transientBottomBar, event)
        if (Snackbar.Callback.DISMISS_EVENT_TIMEOUT == event) {
            callback()
        }
    }
}

/** UNDO ACTION */
fun Activity.undoSnack(message: String, callback: () -> UndoSnackCallback, undoAction: () -> Unit) =
        Snackbar.make(contentView!!, message, Snackbar.LENGTH_LONG)
                .addCallback(callback())
                .setAction("Deshacer") { undoAction() }
                .show()

class UndoSnackCallback(private val preAction: () -> Unit, private val postAction: () -> Unit): Snackbar.Callback() {
    override fun onShown(sb: Snackbar?) {
        super.onShown(sb)
        preAction()
    }
    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
        super.onDismissed(transientBottomBar, event)
        if (Snackbar.Callback.DISMISS_EVENT_TIMEOUT == event) {
            postAction()
        }
    }
}