package com.samsung.superrookie

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val youtubeButton = youtubeButton
        youtubeButton.setOnClickListener {
            val intent = Intent(this, YoutubeActivity::class.java)
            startActivity(intent)
        }
    }
}
