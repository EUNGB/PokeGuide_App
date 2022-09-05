package com.eblee.pokeguide.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.eblee.pokeguide.presentation.utils.observeNotNull

open class BaseFragment : Fragment() {
    protected fun <T> LiveData<T>.observe(observer: (T) -> Unit) = observeNotNull(this@BaseFragment, observer)
}