package com.samsung.superrookie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_youtube.*

class YoutubeActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private var lastX = 0f
    private var lastY = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        val layout = youtubeLayout
        layout.setOnLongClickListener{
            Log.d(TAG, "Long Press -> $lastX, $lastY")
            val fab = FloatingActionButton(this)
            fab.id = View.generateViewId()
            fab.setImageResource(R.drawable.ic_launcher_foreground)
            fab.elevation = 2f
            fab.size = android.support.design.widget.FloatingActionButton.SIZE_MINI
            val layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            fab.x = lastX
            fab.y = lastY
            layout.addView(fab)

            return@setOnLongClickListener true
        }

        layout.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                lastX = event.x
                lastY = event.y
            }
            return@setOnTouchListener false
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            lastX = event.x
            lastY = event.y
        }
        return super.onTouchEvent(event)
    }
}
