package com.dml.base.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import io.reactivex.disposables.CompositeDisposable


abstract class BaseFragment : Fragment() {

    protected var mView: View? = null

    protected var layoutId: Int = 0

    protected var mLanguage: String? = null

    protected var mCompositeDisposable = CompositeDisposable()

    protected abstract fun setLayoutId(): Int

    protected abstract fun connectViews()

    open fun newInstance(bundle: Bundle?): BaseFragment {
        val fragment = Fragment() as BaseFragment
        if (bundle != null)
            fragment.arguments = bundle
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container == null) {
            return null
        }

        layoutId = setLayoutId()
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mView = view
        connectViews()
    }

    override fun getContext(): Context {
        return activity as Context
    }

    fun getParentActivity(): BaseActivity {
        return activity as BaseActivity
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        //super.onActivityResult(requestCode, resultCode, data);
//    }

    override fun onDestroy() {
        super.onDestroy()

        if (!mCompositeDisposable.isDisposed)
            mCompositeDisposable.dispose()
    }
}