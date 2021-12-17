package com.ops.flipclass.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ops.flipclass.R
import kotlinx.android.synthetic.main.app_toolbar_one.*

class MessagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }*/
        llBackButton.setOnClickListener {
            onBackPressed()
        }
    }
}