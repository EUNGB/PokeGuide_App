package com.eblee.pokeguide.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open abstract class BaseViewModel(

) : ViewModel() {

    protected val compositeDisposable : CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        Log.d("BaseViewModel", "disposed")
        compositeDisposable.clear()
        super.onCleared()
    }

    interface Input
    interface Output
    interface Route
}