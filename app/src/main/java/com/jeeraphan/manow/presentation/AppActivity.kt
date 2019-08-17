package com.jeeraphan.manow.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.jeeraphan.manow.R
import com.jeeraphan.manow.core.AppNavigator
import kotlinx.android.synthetic.main.activity_app.*

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        coinButton.setOnClickListener {
            //activity
            //startActivity(Intent(AppNavigator.COIN))

            //fragment
            val coinFragment = Class.forName("com.jeeraphan.manow.coin.CoinFragment")
                    .newInstance() as Fragment
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.appContainerFrameLayout, coinFragment)
            fragmentTransaction.commit()
        }

        couponButton.setOnClickListener {
            startActivity(Intent(AppNavigator.COUPON))
        }

        plaidButton.setOnClickListener {
            startActivity(Intent(this, Class.forName(AppNavigator.PLAID)))

            /**
            startActivity(Intent(AppNavigator.PLAID))
             */

            /**
            val intent = Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME,
            "com.jeeraphan.manow.plaid.PlaidActivity")
            startActivity(intent)
             */

            /**
            val fragment = Class.forName("MyFragment")
            .newInstance() as Fragment
             */
        }
    }
}
