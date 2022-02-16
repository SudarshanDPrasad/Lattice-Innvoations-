package com.application.latticeinnovations.model.remote

/**
 * Response Class
 */
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Source(
    @SerializedName("name")
    val name: String,
)