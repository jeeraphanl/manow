package com.jeeraphan.manow.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jeeraphan.manow.R
import com.jeeraphan.manow.coin.CoinActivity
import com.jeeraphan.manow.coupon.CouponActivity
import kotlinx.android.synthetic.main.activity_home.*

private const val COIN_PACKAGE_NAME = "com.jeeraphan.manow.coin"
private const val COUPON_PACKAGE_NAME = "com.jeeraphan.manow.coupon"
private const val PLAID_PACKAGE_NAME = "com.jeeraphan.manow.plaid"

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        coinButton.setOnClickListener {
            startActivity(Intent(this, CoinActivity::class.java))
        }

        couponButton.setOnClickListener {
            startActivity(Intent(this, CouponActivity::class.java))
        }

        plaidButton.setOnClickListener {
            intent = Intent(this, Class.forName("$PLAID_PACKAGE_NAME.PlaidActivity"))
            startActivity(intent)

            /**
            val intent = Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME,
            "$PACKAGE_NAME.PlaidActivity")
            startActivity(intent)
             */

            /**
            val fragment = Class.forName("MyFragment")
            .newInstance() as Fragment
             */
        }
    }
}
