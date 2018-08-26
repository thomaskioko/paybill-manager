package com.thomaskioko.daraja.util

import com.thomaskioko.daraja.api.util.AppExecutors
import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {
    companion object {
        private val instant = Executor { it.run() }
    }
}
