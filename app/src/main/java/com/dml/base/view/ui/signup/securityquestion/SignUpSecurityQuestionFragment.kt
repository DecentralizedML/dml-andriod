package com.dml.base.view.ui.signup.securityquestion

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.base.BaseFragment
import com.dml.base.network.ErrorResponse
import com.dml.base.view.adapter.SecurityQuestionAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_signup_security_question.*


class SignUpSecurityQuestionFragment : BaseFragment(), SignUpSecurityQuestionContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpSecurityQuestionFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var questionList: ArrayList<String>

    private lateinit var presenter: SignUpSecurityQuestionContract.Presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SignUpSecurityQuestionPresenter(this)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_security_question
    }

    override fun connectViews() {

        saveButton?.apply {
            setText(R.string.fragment_signup_security_button_save)
            setOnClickListener {
            }
        }

        question1Layout.setOnClickListener {
            showQuestionBottomSheetDialog(1)
        }

        question2Layout.setOnClickListener {
            showQuestionBottomSheetDialog(2)
        }
    }

    override fun setPresenter(presenter: SignUpSecurityQuestionContract.Presenter) {
        this.presenter = presenter
    }

    private fun showQuestionBottomSheetDialog(questionId: Int) {
        val mBottomSheetDialog = BottomSheetDialog(context)
        val sheetView = mParentActivity.layoutInflater?.inflate(R.layout.fragment_security_question_bottom_sheet, null)

        questionList = ArrayList()
        questionList.add("What is your favourite sea animal?")
        questionList.add("What is your favourite subject in school?")
        questionList.add("What is your favourite type of music?")
        questionList.add("What is your pet's name?")
        questionList.add("What is the name of your first school?")
        questionList.add("What street did you grow up on?")
        questionList.add("What is your favourite food?")

        val adapter = SecurityQuestionAdapter(context, questionList, object : SecurityQuestionAdapter.OnItemClickListener {
            override fun onClick(question: String) {
                if (questionId == 1) {
                    question1TextView.text = question
                    answer1EditText.setText("")
                } else {
                    question2TextView.text = question
                    answer2EditText.setText("")
                }

                mBottomSheetDialog.dismiss()
            }
        })

        val questionRecyclerView = sheetView?.findViewById(R.id.questionRecyclerView) as RecyclerView
        questionRecyclerView.adapter = adapter
        questionRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()
    }

    override fun showProgressBar() {
    }

    override fun dismissProgressBar() {
    }

    override fun showErrorResponse(errorResponse: ErrorResponse) {
    }
}