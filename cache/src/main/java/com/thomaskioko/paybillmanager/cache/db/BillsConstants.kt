package com.thomaskioko.paybillmanager.cache.db

object BillsConstants {

    const val TABLE_NAME = "bills"

    const val QUERY_BILLS = "SELECT * FROM $TABLE_NAME"

    const val DELETE_BILLS = "DELETE FROM $TABLE_NAME"

}