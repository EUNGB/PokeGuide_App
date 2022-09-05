package com.eblee.pokeguide.presentation.utils

import android.net.Uri
import com.eblee.pokeguide.data.api.URI_MAIN_IMAGE

object ImageUtils {

    fun String.getMainImageUri(): Uri {
        val id = this.dropLast(1).split("/").last()
        return Uri.parse("${URI_MAIN_IMAGE}${id}.svg")
    }

}