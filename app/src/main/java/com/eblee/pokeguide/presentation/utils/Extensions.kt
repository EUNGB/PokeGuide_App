package com.eblee.pokeguide.presentation.utils

import android.text.Editable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner, androidx.lifecycle.Observer { observer(it!!) })
}

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)