package com.dml.base.view.ui.backupseedphrase.confirm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dml.base.R
import com.dml.base.base.BaseFragment
import com.dml.base.network.ErrorResponse
import kotlinx.android.synthetic.main.fragment_backup_seed_phrase_confirm.*
import java.util.*

class BackupSeedPhraseConfirmFragment : BaseFragment(), BackupSeedPhraseConfirmContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = BackupSeedPhraseConfirmFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: BackupSeedPhraseConfirmContract.Presenter
    private lateinit var seedPhrase: String
    private lateinit var phraseList: Array<String>
    private lateinit var questionList: ArrayList<Int>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = BackupSeedPhraseConfirmPresenter(this)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_backup_seed_phrase_confirm
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.fragment_backup_confirm_toolbar_title)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity.onBackPressed()
            })
        }

        copyButton?.apply {
            setText(R.string.fragment_backup_seed_phrase_button_submit)
            setOnClickListener {
                if (checkAnswer())
                    Toast.makeText(context, "true", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "false", Toast.LENGTH_SHORT).show()
            }
        }

        seedPhrase = "witch collapse practice feed shame open despair creek road again ice least"
        setQuestion()
    }

    override fun setPresenter(presenter: BackupSeedPhraseConfirmContract.Presenter) {
        this.presenter = presenter
    }

    private fun setQuestion() {
        phraseList = seedPhrase.split(" ").toTypedArray()
        questionList = ArrayList()

        for (i in 0..3) {
            var number = Random().nextInt(phraseList.size)
            while (questionList.contains(number)) {
                number = Random().nextInt(phraseList.size)
            }
            questionList.add(number)
        }

        order1TextView?.text = (questionList[0] + 1).toString()
        order2TextView?.text = (questionList[1] + 1).toString()
        order3TextView?.text = (questionList[2] + 1).toString()
    }

    private fun checkAnswer(): Boolean {
        return (phrase1EditText.text.toString() == phraseList[questionList[0]]
                && phrase2EditText.text.toString() == phraseList[questionList[1]]
                && phrase3EditText.text.toString() == phraseList[questionList[2]])
    }

    override fun showProgressBar() {
    }

    override fun dismissProgressBar() {
    }

    override fun showErrorResponse(errorResponse: ErrorResponse) {
    }
}