package com.ops.flipclass

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.android.synthetic.main.app_toolbar_two.*
import kotlinx.android.synthetic.main.fragment_profile_details.*

class MyProfileActivity : AppCompatActivity() {

    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        /*window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT*/

        mSharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE)
        editor = mSharedPreferences.edit()

        tv_userNameProfile.text = mSharedPreferences.getString("userName", "Error")
        tv_userEmailProfile.text = mSharedPreferences.getString("userEmail", "Error")
        val photo = mSharedPreferences.getString("userPhoto", "Error")
        Glide.with(this).load((photo).toString()).into(civ_user_image_profile)
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

        llBackButton.setOnClickListener {
            onBackPressed()
        }
    }
}