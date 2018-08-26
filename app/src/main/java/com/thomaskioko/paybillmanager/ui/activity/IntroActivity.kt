package com.thomaskioko.paybillmanager.ui.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.codemybrainsout.onboarder.AhoyOnboarderActivity
import com.codemybrainsout.onboarder.AhoyOnboarderCard
import com.thomaskioko.paybillmanager.R
import com.thomaskioko.paybillmanager.util.SharedPrefsUtil
import dagger.android.AndroidInjection
import javax.inject.Inject


class IntroActivity : AhoyOnboarderActivity() {

    @Inject
    lateinit var sharedPrefsUtil: SharedPrefsUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        var cardHi = AhoyOnboarderCard(applicationContext.getString(R.string.intro_title_hi), applicationContext.getString(R.string.intro_message_hi), R.drawable.ic_drawable_hi)
        cardHi = styleCards(cardHi)

        var cardSaveBills = AhoyOnboarderCard(applicationContext.getString(R.string.intro_title_save_bills), applicationContext.getString(R.string.intro_message_save_bills), R.drawable.ic_save_bills)
        cardSaveBills = styleCards(cardSaveBills)

        var cardNotification = AhoyOnboarderCard(applicationContext.getString(R.string.intro_title_notifications), applicationContext.getString(R.string.intro_message_notifications), R.drawable.ic_notification)
        cardNotification = styleCards(cardNotification)

        var cardReceipt = AhoyOnboarderCard(applicationContext.getString(R.string.intro_title_overview), applicationContext.getString(R.string.intro_message_overview), R.drawable.ic_graph)
        cardReceipt = styleCards(cardReceipt)

        var cardStart = AhoyOnboarderCard(applicationContext.getString(R.string.intro_title_start), applicationContext.getString(R.string.intro_message_start), R.drawable.ic_begin)
        cardStart = styleCards(cardStart)

        val pages = mutableListOf(
                cardHi,
                cardSaveBills,
                cardNotification,
                cardReceipt,
                cardStart
        )

        val typeface = Typeface.createFromAsset(applicationContext.assets, "fonts/WorkSans-Regular.otf")
        setFont(typeface)

        setGradientBackground()

        setFinishButtonDrawableStyle(ContextCompat.getDrawable(this@IntroActivity, R.drawable.drawable_intro_button))
        setFinishButtonTitle("Get Started")
        setOnboardPages(pages)
    }

    override fun onFinishButtonPressed() {
        sharedPrefsUtil.setHasSeenIntroScreen(true)
        startActivity(Intent(this@IntroActivity, MainActivity::class.java))
    }

    private fun styleCards(card: AhoyOnboarderCard): AhoyOnboarderCard {
        card.setBackgroundColor(R.color.black_transparent)
        card.setTitleColor(R.color.white)
        card.setDescriptionColor(R.color.white)
        card.setTitleTextSize(dpToPixels(10, this))
        return card
    }

}
