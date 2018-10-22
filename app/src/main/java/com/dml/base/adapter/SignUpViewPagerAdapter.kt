package com.dml.base.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dml.base.fragment.*


class SignUpViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    companion object {
        const val NUM_ITEMS = 6

        const val PAGE_SIGNUP = 0
        const val PAGE_GOOGLE = 1
        const val PAGE_INFORMATION = 2
        const val PAGE_CONNECT = 3
        const val PAGE_WALLET = 4
        const val PAGE_COMPLETE = 5
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment? {
        return when (position) {
            PAGE_SIGNUP -> SignUpFragment.newInstance(null)
            PAGE_GOOGLE -> SignUpGoogleFragment.newInstance(null)
            PAGE_INFORMATION -> SignUpInformationFragment.newInstance(null)
            PAGE_CONNECT -> SignUpConnectFragment.newInstance(null)
            PAGE_WALLET -> SignUpWalletFragment.newInstance(null)
            PAGE_COMPLETE -> SignUpCompleteFragment.newInstance(null)
            else -> null
        }
    }
}