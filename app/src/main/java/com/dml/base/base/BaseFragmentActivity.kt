package com.dml.base.base

import android.view.View
import android.widget.FrameLayout
import com.dml.base.R

abstract class BaseFragmentActivity : BaseActivity() {

    var mFragmentLayout: FrameLayout? = null

    protected abstract fun createBaseFragment()

    override fun onContentChanged() {
        super.onContentChanged()

        mFragmentLayout = findViewById<View>(R.id.fragment_container) as FrameLayout
        if (mFragmentLayout != null) {
            connectViews()
            createBaseFragment()
        }
    }

    override fun onBackPressed() {
//        if (drawer_layout?.isDrawerOpen(GravityCompat.START) == true) {
//            drawer_layout?.closeDrawer(GravityCompat.END)
//            return
//        }

        super.onBackPressed()

//        if (supportFragmentManager.backStackEntryCount > 0) {
//            mFragmentTransaction = supportFragmentManager.beginTransaction()
//            mFragmentTransaction?.setCustomAnimations(R.anim.exit, R.anim.enter)
//            supportFragmentManager.popBackStack()
//        } else {
//            super.onBackPressed()
//        }

//        var currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
//        if (currentFragment is DashboardFragment) {
//
//        } else if (currentFragment is InboxListingFragment || currentFragment is ProfileHomePageFragment || currentFragment is CouponListFragment) {
//            for (i in 0..supportFragmentManager.backStackEntryCount) {
//                supportFragmentManager.popBackStack()
//            }
//        } else if (currentFragment is SettingFragment) {
//            if (fragmentManager.backStackEntryCount > 0) {
//                for (i in 0..supportFragmentManager.backStackEntryCount) {
//                    supportFragmentManager.popBackStack()
//                }
//            } else {
//                startFragment(DashboardFragment.newInstance(), false)
//            }
//        } else {
//            super.onBackPressed()
//        }
    }

    protected fun setStartAnimation() {

    }

    protected fun setEndAnimation() {

    }
}