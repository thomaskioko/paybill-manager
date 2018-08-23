package com.thomaskioko.paybillmanager.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thomaskioko.paybillmanager.cache.dao.BillsDao
import com.thomaskioko.paybillmanager.cache.model.CachedBills
import javax.inject.Inject

@Database(entities = [
    CachedBills::class
], version = 1)
abstract class PayBillManagerDatabase @Inject constructor() : RoomDatabase() {

    abstract fun billsDao(): BillsDao

    companion object {
        private var INSTANCE: PayBillManagerDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): PayBillManagerDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                PayBillManagerDatabase::class.java, "bills.db")
                                .build()
                    }
                    return INSTANCE as PayBillManagerDatabase
                }
            }
            return INSTANCE as PayBillManagerDatabase
        }
    }
}