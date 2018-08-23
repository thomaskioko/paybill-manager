package com.thomaskioko.paybillmanager.cache.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thomaskioko.paybillmanager.cache.dao.BillsDao
import javax.inject.Inject

abstract class PayBillManagerDatabase @Inject constructor(): RoomDatabase() {

    abstract fun billsDao() : BillsDao

    companion object {
        private var INSTANCE: PayBillManagerDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): PayBillManagerDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                PayBillManagerDatabase::class.java, "projects.db")
                                .build()
                    }
                    return INSTANCE as PayBillManagerDatabase
                }
            }
            return INSTANCE as PayBillManagerDatabase
        }
    }
}