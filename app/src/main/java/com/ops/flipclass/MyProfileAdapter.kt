package com.ops.flipclass

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyProfileAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        return when(position){

            0->{
                MyEducationFragment()
            }
            1->{
                ProfileDetailsFragment()
            }
            2->{
                ExperienceFragment()
            }
            else->{
                Fragment()
            }
        }
    }
}