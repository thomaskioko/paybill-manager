package com.thomaskioko.paybillmanager.mobile.mapper

interface ViewMapper<in P, out V> {

    fun mapToView(presentation: P): V

}