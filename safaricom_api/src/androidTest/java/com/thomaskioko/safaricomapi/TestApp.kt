package com.thomaskioko.safaricomapi

import android.app.Application

/**
 * We use a separate App for tests to prevent initializing dependency injection.
 *
 * See [com.thomaskioko.safaricomapi.util.SafaricomTestRunner].
 */
class TestApp : Application()