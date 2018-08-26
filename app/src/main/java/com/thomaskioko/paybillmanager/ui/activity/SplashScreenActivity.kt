package com.thomaskioko.paybillmanager.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.thomaskioko.paybillmanager.R
import com.thomaskioko.paybillmanager.ui.base.BaseActivity
import com.thomaskioko.paybillmanager.util.SharedPrefsUtil
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SplashScreenActivity : BaseActivity() {

    @Inject
    lateinit var sharedPrefsUtil: SharedPrefsUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            Schedulers.io().createWorker().schedule {

                if (sharedPrefsUtil.getHasSeenIntroScreen()) {
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashScreenActivity, IntroActivity::class.java))
                }
                finish()

            }
        }, 1000)
    }


}