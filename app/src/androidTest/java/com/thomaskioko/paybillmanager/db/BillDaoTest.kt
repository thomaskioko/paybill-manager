package com.thomaskioko.paybillmanager.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test the implementation of [BillsDao]
 */
@RunWith(AndroidJUnit4::class)
class BillDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: PaybillDatabase

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears after test
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PaybillDatabase::class.java)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        database.close()
    }


    @Test
    fun getUsersWhenNoUserInserted() {
        database.billsDao().getBillByPayBillNumber("123")
                .test()
                .assertNoValues()
    }
}