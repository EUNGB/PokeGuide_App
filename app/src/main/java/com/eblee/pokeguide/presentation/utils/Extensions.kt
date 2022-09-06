package com.eblee.pokeguide.presentation.utils

import android.text.Editable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner, androidx.lifecycle.Observer { observer(it!!) })
}

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

fun String.getApiId(): Int = this.split("/".toRegex()).dropLast(1).last().toInt()

fun String.isNumber(): Boolean {
    return if (this.isEmpty()) false else this.all { Character.isDigit(it) }
}