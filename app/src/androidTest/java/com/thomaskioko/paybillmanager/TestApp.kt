package com.thomaskioko.paybillmanager

import android.app.Application

/**
 * We use a separate App for tests to prevent initializing dependency injection.
 *
 * See [com.android.example.github.util.GithubTestRunner].
 */
class TestApp : Application()
