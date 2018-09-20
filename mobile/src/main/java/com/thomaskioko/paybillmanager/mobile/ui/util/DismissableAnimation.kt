package com.thomaskioko.paybillmanager.mobile.ui.util

interface DismissableAnimation {

    interface OnDismissedListener {
        fun onDismissed()
    }

    fun dismiss(listner: OnDismissedListener)
}