package com.thomaskioko.paybillmanager.cache.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
        tableName = "bill_category_join",
        primaryKeys = ["billId", "categoryId"],
        foreignKeys = [
            ForeignKey(
                    entity = CachedBill::class,
                    parentColumns = ["id"],
                    childColumns = ["billId"]
            ),
            ForeignKey(
                    entity = CachedCategory::class,
                    parentColumns = ["id"],
                    childColumns = ["categoryId"]
            )
        ]
)
data class CachedBillCategory(
        val billId: String,
        val categoryId: String
)