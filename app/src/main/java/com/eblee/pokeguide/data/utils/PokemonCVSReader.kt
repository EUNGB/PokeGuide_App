package com.eblee.pokeguide.data.utils

import android.content.Context
import com.eblee.pokeguide.R
import com.opencsv.CSVReader
import java.io.BufferedReader
import java.io.InputStreamReader

object PokemonCVSReader {

    fun Context.getPokemonNamesByKorean(): List<Array<String>> {
        val records = mutableListOf<Array<String>>()
        try {
            val inputStreamReader = InputStreamReader(this.resources.openRawResource(R.raw.names))
            val reader = BufferedReader(inputStreamReader)
            val read = CSVReader(reader)
            var record: Array<String>
            while (read.readNext().also { record = it } != null) {
                if (record[1] == "3") {
                    records.add(record)
                }
            }
            return records
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return records
    }
}