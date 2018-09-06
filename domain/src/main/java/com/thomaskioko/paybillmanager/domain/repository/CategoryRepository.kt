package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.Category
import io.reactivex.Completable
import io.reactivex.Observable

interface CategoryRepository {

    fun getCategories(): Observable<List<Category>>

    fun getCategoryById(categoryId: String): Observable<Category>

    fun createCategory(category: Category): Completable

    fun createCategories(categories: List<Category>): Completable

    fun updateCategory(category: Category): Completable
}