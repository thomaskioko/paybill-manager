package com.thomaskioko.paybillmanager.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CachedCategory(
        @PrimaryKey
        var id: String,
        var categoryName: String,
        var drawableUrl: String
)