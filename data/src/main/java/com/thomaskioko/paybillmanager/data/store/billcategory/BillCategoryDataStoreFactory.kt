package com.thomaskioko.paybillmanager.data.store.billcategory

import com.thomaskioko.paybillmanager.data.repository.billcategory.BillCategoryDataStore
import javax.inject.Inject

open class BillCategoryDataStoreFactory @Inject
constructor(
        private val billCategoryDataStore: BillCategoryDataStore
) {
    open fun getCacheDataStore(): BillCategoryDataStore {
        return billCategoryDataStore
    }
}