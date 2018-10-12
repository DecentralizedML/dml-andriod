package com.dml.base.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dml.base.fragment.SignUpCompleteFragment
import com.dml.base.fragment.SignUpFirstFragment
import com.dml.base.fragment.SignUpSecondFragment
import com.dml.base.fragment.SignUpThirdFragment


class SignUpViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    companion object {
        private const val NUM_ITEMS = 4
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment? {
        return when (position) {
            0 -> SignUpFirstFragment.newInstance(null)
            1 -> SignUpSecondFragment.newInstance(null)
            2 -> SignUpThirdFragment.newInstance(null)
            3 -> SignUpCompleteFragment.newInstance(null)
            else -> null
        }
    }
}