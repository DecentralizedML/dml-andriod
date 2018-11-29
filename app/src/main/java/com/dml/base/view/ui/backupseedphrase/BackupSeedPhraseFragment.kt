package com.dml.base.view.ui.backupseedphrase

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseFragment
import com.dml.base.network.ErrorResponse
import com.dml.base.view.adapter.SeedPhraseAdapter
import com.dml.base.view.ui.backupseedphrase.confirm.BackupSeedPhraseConfirmFragment
import kotlinx.android.synthetic.main.fragment_backup_seed_phrase.*

class BackupSeedPhraseFragment : BaseFragment(), BackupSeedPhraseContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = BackupSeedPhraseFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: BackupSeedPhraseContract.Presenter
    private lateinit var seedPhrase: String
    private var isCopied = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = BackupSeedPhrasePresenter(this)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_backup_seed_phrase
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.fragment_backup_toolbar_title)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity.onBackPressed()
            })
        }

        copyButton?.apply {
            setText(R.string.fragment_backup_seed_phrase_button_copy)
            setOnClickListener {
                if (isCopied) {
                    startFragment(BackupSeedPhraseConfirmFragment.newInstance(null), BackupSeedPhraseConfirmFragment::class.java.simpleName, false)
                } else {
                    isCopied = true
                    setBackground(R.drawable.button_white)
                    setText(R.string.fragment_backup_seed_phrase_button_copied)
                    setTextColor(R.color.colorAccent)
                    showRightIcon(true)
                    setRightIconColor(R.color.colorAccent)
                    Utility.setClipboard(context, "", seedPhrase)
                }
            }
        }

        isCopied = false
        seedPhrase = "witch collapse practice feed shame open despair creek road again ice least"
        setSeedPhraseAdapter()
    }

    override fun setPresenter(presenter: BackupSeedPhraseContract.Presenter) {
        this.presenter = presenter
    }

    private fun setSeedPhraseAdapter() {
        val phraseList = seedPhrase.split(" ").toTypedArray()

        val adapter = SeedPhraseAdapter(context, phraseList, object : SeedPhraseAdapter.OnItemClickListener {
            override fun onClick(title: String) {
//                Toast.makeText(context, "clicked item $title", Toast.LENGTH_SHORT).show()
            }
        })
        seedPhraseRecyclerView?.adapter = adapter
        seedPhraseRecyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    override fun showProgressBar() {
    }

    override fun dismissProgressBar() {
    }

    override fun showErrorResponse(errorResponse: ErrorResponse) {
    }
}