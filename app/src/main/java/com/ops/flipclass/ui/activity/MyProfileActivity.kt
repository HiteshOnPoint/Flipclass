package com.ops.flipclass.ui.activity

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ops.flipclass.ui.adapter.MyProfileAdapter
import com.ops.flipclass.R
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.android.synthetic.main.app_toolbar_two.*
import android.view.WindowManager

import android.os.Build
import android.view.Window
import com.ops.flipclass.utilities.SharedPrefsUtils


class MyProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }*/

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#4287FF")

        civ_backButton.visibility = View.VISIBLE
        tvToolbarTitle.visibility = View.VISIBLE
        civ_loggedInUserImage.visibility = View.VISIBLE

        Glide.with(this).load(R.drawable.blue_back_button).into(civ_backButton)
        tvToolbarTitle.text = "My Profile"
        tvToolbarTitle.setTextColor(Color.parseColor("#FFFFFF"))

        tv_userNameProfile.text = SharedPrefsUtils.getStringPreference(this,"userName")
        tv_userEmailProfile.text = SharedPrefsUtils.getStringPreference(this, "userEmail")
        val photo = SharedPrefsUtils.getStringPreference(this,"userPhoto")
        Glide.with(this).load((photo).toString()).into(civ_loggedInUserImage)
        Glide.with(this).load((photo).toString()).into(civ_user_image_my_profile)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)

        val adapter = MyProfileAdapter(supportFragmentManager, lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout,viewPager2){tab, position ->

            when(position){

                0->{
                    tab.text = "My Education"
                }
                1->{
                    tab.text = "Profile Details"
                }
                2->{
                    tab.text = "Experience"
                }
            }
        }.attach()

        civ_backButton.setOnClickListener {
            onBackPressed()
        }
    }
}