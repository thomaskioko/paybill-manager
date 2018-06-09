package com.thomaskioko.daraja

import android.app.Application

/**
 * We use a separate App for tests to prevent initializing dependency injection.
 *
 * See [com.thomaskioko.daraja.util.SafaricomTestRunner].
 */
class TestApp : Application()