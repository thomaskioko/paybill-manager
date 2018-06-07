package com.thomaskioko.safaricomapi.util

import com.thomaskioko.daraja.repository.api.util.AppExecutors
import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {
    companion object {
        private val instant = Executor { it.run() }
    }
}
