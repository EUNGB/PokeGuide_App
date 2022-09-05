package com.eblee.pokeguide.presentation.view.splash

import com.eblee.pokeguide.domain.use_case.UCSavePokemonNames
import com.eblee.pokeguide.presentation.base.BaseViewModel

class SplashViewModel(
    private val savePokemonNames: UCSavePokemonNames
) : BaseViewModel() {

    fun saveName(record: Array<String>) {
        if (record.size < 4) return
        savePokemonNames.invoke(record)
    }

}