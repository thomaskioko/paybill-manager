package com.thomaskioko.paybillmanager.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thomaskioko.daraja.db.converter.DateTimeTypeConverters
import com.thomaskioko.paybillmanager.db.dao.BillsDao
import com.thomaskioko.paybillmanager.db.entity.Bill
import com.thomaskioko.paybillmanager.db.entity.BillCategory


@Database(
        entities = [
            Bill::class,
            BillCategory::class
        ],
        version = 1,
        exportSchema = false
)
@TypeConverters(DateTimeTypeConverters::class)
abstract class PaybillDatabase : RoomDatabase() {

    abstract fun billsDao(): BillsDao

    abstract fun billCategoryDao(): BillCategory

}