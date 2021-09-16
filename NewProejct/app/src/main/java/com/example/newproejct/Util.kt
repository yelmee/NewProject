package com.example.newproejct

import android.app.Activity
import com.google.android.material.snackbar.Snackbar

fun Activity.snackbar(msg: String, action: (() -> Unit)? = null) {
    Snackbar.make(
        findViewById(android.R.id.content),
        msg,
        Snackbar.LENGTH_LONG
    ).also {
        it.setAction(
            "Ok"
        ){action?.invoke()}
    }.show()
}