package com.jeeraphan.manow.coin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jeeraphan.manow.core.AppNavigator
import kotlinx.android.synthetic.main.activity_coin.*

class CoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)

        textView.setOnClickListener {
            startActivity(Intent(AppNavigator.COUPON))
        }
    }
}
