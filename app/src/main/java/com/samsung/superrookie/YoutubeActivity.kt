package com.samsung.superrookie

import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_youtube.*

class YoutubeActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private var lastX = 0f
    private var lastY = 0f
    private val buffer: ArrayList<FloatingActionButton> = ArrayList()
    private val layout by lazy {
        youtubeLayout
    }

    private val handler = Handler()
    private val runnable = Runnable {
        Log.d(TAG, "long touch")
        val fab = makeFloatingActionButton(lastX - 100f, lastY - 100f)

        val face1 = makeFloatingActionButton(lastX - 70f, lastY - 250f)
        val face2 = makeFloatingActionButton(lastX - 200f, lastY - 200f)
        val face3 = makeFloatingActionButton(lastX - 250f, lastY - 80f)
        val face4 = makeFloatingActionButton(lastX - 190f, lastY + 40f)

        layout.addView(fab)
        layout.addView(face1)
        layout.addView(face2)
        layout.addView(face3)
        layout.addView(face4)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        layout.setOnLongClickListener{
            Log.d(TAG, "Long Press -> $lastX, $lastY")
            val fab = makeFloatingActionButton(lastX - 100f, lastY - 100f)

            val face1 = makeFloatingActionButton(lastX - 70f, lastY - 250f)
            val face2 = makeFloatingActionButton(lastX - 200f, lastY - 200f)
            val face3 = makeFloatingActionButton(lastX - 250f, lastY - 80f)
            val face4 = makeFloatingActionButton(lastX - 190f, lastY + 40f)

            layout.addView(fab)
            layout.addView(face1)
            layout.addView(face2)
            layout.addView(face3)
            layout.addView(face4)

            return@setOnLongClickListener true
        }

        layout.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastX = event.x
                    lastY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                }
                MotionEvent.ACTION_UP -> {
                    val x = event.x
                    val y = event.y
                    for (i in 1..buffer.size-1) {
                        val it = buffer.get(i)
                        val width = it.width
                        val height = it.height
                        if (x >= it.x && x <= it.x + width && y >= it.y && y <= it.y + height) {
                            Log.d(TAG, "touched $it")
                            makeDialog().show()
                            break
                        }
                    }

                    buffer.forEach {
                        layout.removeView(it)
                    }
                    buffer.clear()
                }
            }
            return@setOnTouchListener false
        }
    }

    private fun makeFloatingActionButton(x:Float, y:Float): FloatingActionButton {
        val fab = FloatingActionButton(this)
        fab.id = View.generateViewId()
        fab.setImageResource(R.drawable.ic_launcher_foreground)
        fab.elevation = 2f
        fab.size = android.support.design.widget.FloatingActionButton.SIZE_MINI
        val layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        fab.x = x
        fab.y = y
        fab.isFocusable = true
        fab.setOnTouchListener { v, event ->
            Log.d(TAG, "in fab -> onTouch()")
            return@setOnTouchListener true
        }
        buffer.add(fab)
        return fab
    }

    private fun makeDialog(): Dialog {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("이 화면을 공유할까요?")
                .setPositiveButton("네", {
                    dialog, id -> Toast.makeText(this, "공유~", Toast.LENGTH_LONG).show()
                })
                .setNegativeButton("아니오", DialogInterface.OnClickListener {
                    dialog, id -> Toast.makeText(this, "안해~", Toast.LENGTH_LONG).show()
                })
        return builder.create()
    }
}
