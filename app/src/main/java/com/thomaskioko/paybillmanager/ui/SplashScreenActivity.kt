package com.thomaskioko.paybillmanager.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.thomaskioko.paybillmanager.R
import io.reactivex.schedulers.Schedulers


class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            Schedulers.io().createWorker().schedule {
                startActivity(Intent(this@SplashScreenActivity, IntroActivity::class.java))
            }
            finish()
        }, 1500)
    }
}