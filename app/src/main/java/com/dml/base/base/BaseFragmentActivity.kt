package com.dml.base.base

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import android.text.TextUtils
import android.view.View
import android.widget.FrameLayout
import com.dml.base.R

abstract class BaseFragmentActivity : BaseActivity() {

    var mFragmentLayout: FrameLayout? = null

    var mFragmentTransaction: FragmentTransaction? = null

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

    fun startFragment(fragment: BaseFragment, isAnimation: Boolean) {
        startFragment(fragment, null, isAnimation)
    }

    fun startFragment(fragment: BaseFragment, tag: String?, isAnimation: Boolean) {
        mFragmentTransaction = supportFragmentManager.beginTransaction()

        if (isAnimation) {
            mFragmentTransaction?.setCustomAnimations(R.anim.enter, R.anim.exit)
        }
        if (TextUtils.isEmpty(tag)) {
            mFragmentTransaction?.replace(R.id.fragment_container, fragment)
        } else {
            mFragmentTransaction?.replace(R.id.fragment_container, fragment, tag)
            mFragmentTransaction?.addToBackStack(tag)
        }

        mFragmentTransaction?.commitAllowingStateLoss()
    }

    fun addFragment(fragment: BaseFragment, tag: String?, isAnimation: Boolean) {
        mFragmentTransaction = supportFragmentManager.beginTransaction()

        if (isAnimation) {
            mFragmentTransaction?.setCustomAnimations(R.anim.enter, R.anim.exit)
        }
        if (TextUtils.isEmpty(tag)) {
            mFragmentTransaction?.add(R.id.fragment_container, fragment)
        } else {
            mFragmentTransaction?.add(R.id.fragment_container, fragment, tag)
            mFragmentTransaction?.addToBackStack(tag)
        }

        mFragmentTransaction?.commitAllowingStateLoss()
    }

    fun finishFragment(fragment: Fragment, isAnimation: Boolean) {
        var isAnimation = isAnimation
        isAnimation = false

        mFragmentTransaction = supportFragmentManager.beginTransaction()

        if (!isAnimation) {
            //            mFragmentTransaction.setCustomAnimations(0, 0, 0, 0);
        }

        mFragmentTransaction?.remove(fragment)
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        }

        mFragmentTransaction?.commitAllowingStateLoss()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
//        if (fragment != null)
//            fragment.onActivityResult(requestCode, resultCode, data)
//    }
}