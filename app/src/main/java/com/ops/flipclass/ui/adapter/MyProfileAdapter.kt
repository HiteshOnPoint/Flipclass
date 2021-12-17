package com.ops.flipclass.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ops.flipclass.ui.fragement.ExperienceFragment
import com.ops.flipclass.ui.fragement.MyEducationFragment
import com.ops.flipclass.ui.fragement.ProfileDetailsFragment

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