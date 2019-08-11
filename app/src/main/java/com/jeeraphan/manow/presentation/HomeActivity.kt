package com.jeeraphan.manow.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jeeraphan.manow.R
import com.jeeraphan.manow.coin.CoinActivity
import com.jeeraphan.manow.coupon.CouponActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
private const val COIN_PACKAGE_NAME = "com.jeeraphan.manow.coin"
private const val COUPON_PACKAGE_NAME = "com.jeeraphan.manow.coupon"
*/

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        coinButton.setOnClickListener {

            /**
            val intent = Intent(this, CoinActivity::class.java)
            startActivity(intent)
             */

            startActivity(Intent(this, CoinActivity::class.java))

            /**
            val intent = Intent(Intent.ACTION_VIEW).setClassName(
            COIN_PACKAGE_NAME,
            "$COIN_PACKAGE_NAME.CoinActivity")
            startActivity(intent)
             */

            /**
            val fragment = Class.forName("MyFragment")
            .newInstance() as Fragment
             */
        }

        couponButton.setOnClickListener {
            /**
            val intent = Intent(Intent.ACTION_VIEW).setClassName(
            COUPON_PACKAGE_NAME,
            "$packageName.CouponActivity")
            startActivity(intent)
             */

            startActivity(Intent(this, CouponActivity::class.java))
        }
    }
}
