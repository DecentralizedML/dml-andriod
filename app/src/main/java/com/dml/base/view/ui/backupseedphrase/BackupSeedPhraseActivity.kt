package com.dml.base.view.ui.backupseedphrase

import com.dml.base.R
import com.dml.base.base.BaseFragmentActivity

class BackupSeedPhraseActivity : BaseFragmentActivity() {

    override fun createBaseFragment() {
        startFragment(BackupSeedPhraseFragment.newInstance(null), BackupSeedPhraseFragment::class.java.simpleName, false)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_backup_seed_phrase
    }

    override fun connectViews() {
    }
}