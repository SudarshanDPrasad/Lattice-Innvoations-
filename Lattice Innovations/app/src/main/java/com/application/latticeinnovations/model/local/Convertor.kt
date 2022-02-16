package com.application.latticeinnovations.model.local

import androidx.room.TypeConverter
import com.application.latticeinnovations.model.remote.Source

/**
 * Convertor Class to merge the 2 list
 */
class Convertor {

    @TypeConverter
    fun sourceChange(source: Source): String {
        return source.name!!
    }

    @TypeConverter
    fun returnSource(name: String): Source {
        return Source(name)
    }
}