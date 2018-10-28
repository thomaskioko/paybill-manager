package com.thomaskioko.paybillmanager.mobile.util

import android.view.View
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher

object EspressoTextInputLayoutUtils {

    fun withErrorInInputLayout(stringMatcher: Matcher<String>): Matcher<View> {
        Preconditions.checkNotNull(stringMatcher)

        return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
            var actualError = ""

            override fun describeTo(description: Description) {
                description.appendText("with error: ")
                stringMatcher.describeTo(description)

                description.appendText("But got: $stringMatcher.describeTo(description)")
            }

            public override fun matchesSafely(textInputLayout: TextInputLayout): Boolean {
                val error = textInputLayout.error
                if (error != null) {
                    actualError = error.toString()
                    return stringMatcher.matches(actualError)
                }
                return false
            }
        }
    }

    fun withErrorInInputLayout(string: String): Matcher<View> {
        return withErrorInInputLayout(CoreMatchers.`is`(string))
    }
}
