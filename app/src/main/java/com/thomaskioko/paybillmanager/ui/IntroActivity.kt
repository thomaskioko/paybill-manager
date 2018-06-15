package com.thomaskioko.paybillmanager.ui

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.codemybrainsout.onboarder.AhoyOnboarderActivity
import com.codemybrainsout.onboarder.AhoyOnboarderCard
import com.thomaskioko.paybillmanager.R


class IntroActivity : AhoyOnboarderActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var cardAddBills = AhoyOnboarderCard("Hola!", "You can call me Bill. The only Manager you'll love. Let me show you around.", R.drawable.ic_drawable_hi)
        cardAddBills = styleCards(cardAddBills)

        var cardSaveBills = AhoyOnboarderCard("Save Bills", "No stress, you never have to worry about remembering long numbers.", R.drawable.ic_save_bills)
        cardSaveBills = styleCards(cardSaveBills)

        var cardNotification = AhoyOnboarderCard("Notifications", "Get notifications before your bill is due.  I've got your back", R.drawable.ic_notification)
        cardNotification = styleCards(cardNotification)

        var cardReceipt = AhoyOnboarderCard("Overview", "View how much you are spending on your bills. I'll track them for you", R.drawable.ic_graph)
        cardReceipt = styleCards(cardReceipt)

        var cardStart = AhoyOnboarderCard("Let's Go", "Let me be your Bill keeper", R.drawable.ic_begin)
        cardStart = styleCards(cardStart)

        val pages = mutableListOf(
                cardAddBills,
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
