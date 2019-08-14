package com.jeeraphan.manow.plaid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jeeraphan.manow.core.AppNavigator
import kotlinx.android.synthetic.main.activity_plaid.*

class PlaidActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plaid)

        textView.setOnClickListener {
            startActivity(Intent(AppNavigator.COIN))
        }
    }
}
