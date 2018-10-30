package com.dml.base.base

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.dml.base.R
import com.dml.base.network.api.service.APIService
import io.reactivex.disposables.CompositeDisposable


abstract class BaseActivity : AppCompatActivity() {

    var mFragmentTransaction: FragmentTransaction? = null

    val mService = APIService()

    var mCompositeDisposable = CompositeDisposable()

    protected abstract fun setLayoutId(): Int

    protected abstract fun connectViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        connectViews()
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
//            mFragmentTransaction?.setCustomAnimations(R.anim.exit, R.anim.enter)
        }

        mFragmentTransaction?.remove(fragment)
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        }

        mFragmentTransaction?.commitAllowingStateLoss()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (!mCompositeDisposable.isDisposed)
            mCompositeDisposable.dispose()
    }
}