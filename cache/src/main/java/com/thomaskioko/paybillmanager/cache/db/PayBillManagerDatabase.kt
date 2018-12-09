package com.thomaskioko.paybillmanager.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thomaskioko.paybillmanager.cache.dao.BillsDao
import com.thomaskioko.paybillmanager.cache.dao.CategoryDao
import com.thomaskioko.paybillmanager.cache.dao.ConfigDao
import com.thomaskioko.paybillmanager.cache.dao.TokenDao
import com.thomaskioko.paybillmanager.cache.model.CachedBills
import com.thomaskioko.paybillmanager.cache.model.CachedCategory
import com.thomaskioko.paybillmanager.cache.model.CachedToken
import com.thomaskioko.paybillmanager.cache.model.Config
import javax.inject.Inject

@Database(entities = [
    CachedBills::class,
    CachedToken::class,
    CachedCategory::class,
    Config::class
], version = 1, exportSchema = false)
abstract class PayBillManagerDatabase @Inject constructor() : RoomDatabase() {

    abstract fun billsDao(): BillsDao

    abstract fun tokenDao(): TokenDao

    abstract fun configDao(): ConfigDao

    abstract fun categoryDaoDao(): CategoryDao


    companion object {
        private var INSTANCE: PayBillManagerDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): PayBillManagerDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                PayBillManagerDatabase::class.java, "paybill_manager.db")
                                .build()
                    }
                    return INSTANCE as PayBillManagerDatabase
                }
            }
            return INSTANCE as PayBillManagerDatabase
        }
    }
}