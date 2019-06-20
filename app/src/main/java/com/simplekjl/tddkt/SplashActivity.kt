package com.simplekjl.tddkt

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        button.setOnClickListener { getStarted() }
        startLogoAnimation()
    }

    private fun startLogoAnimation() {
        val animator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(3000)
        animator.repeatCount = LottieDrawable.INFINITE
//        val animator : ValueAnimator = ValueAnimator.ofFloat(0f,0.09f).setDuration(300)
        animator.addUpdateListener { logo.progress = it.animatedValue as Float }

        if (logo.progress == 0f) {
            animator.start()
        } else {
            logo.progress = 0f
        }
    }


    fun getStarted() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
