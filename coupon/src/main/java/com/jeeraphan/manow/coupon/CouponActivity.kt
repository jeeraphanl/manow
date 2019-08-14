package com.jeeraphan.manow.coupon

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jeeraphan.manow.core.AppNavigator
import kotlinx.android.synthetic.main.activity_coupon.*

class CouponActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon)

        textView.setOnClickListener {
            startActivity(Intent(AppNavigator.COIN))
        }
    }
}
