package com.ops.flipclass.ui.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ops.flipclass.ui.adapter.MyProfileAdapter
import com.ops.flipclass.R
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.app_toolbar_two.*

class MyProfileActivity : AppCompatActivity() {

    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

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