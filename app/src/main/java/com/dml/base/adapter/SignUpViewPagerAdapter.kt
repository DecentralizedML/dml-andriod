package com.dml.base.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dml.base.fragment.*


class SignUpViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    companion object {
        private const val NUM_ITEMS = 5
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment? {
        return when (position) {
            0 -> SignUpFirstFragment.newInstance(null)
            1 -> SignUpSecondFragment.newInstance(null)
            2 -> SignUpThirdFragment.newInstance(null)
            3 -> SignUpWalletFragment.newInstance(null)
            4 -> SignUpCompleteFragment.newInstance(null)
            else -> null
        }
    }
}