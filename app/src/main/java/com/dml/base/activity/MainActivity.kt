package com.dml.base.activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.adapter.JobAdapter
import com.dml.base.adapter.JobHistoryAdapter
import com.dml.base.base.BaseActivity
import com.dml.base.model.JobHistoryModel
import com.dml.base.model.JobModel
import com.dml.base.ui.CenterZoomLayoutManager
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setJobAdapter()
        setJobHistoryAdapter()
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.current_balance)
            setRightButton(R.drawable.ic_action_settings, View.OnClickListener {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))

            })
            setWalletButton(View.OnClickListener {
                startActivity(Intent(this@MainActivity, WalletActivity::class.java))
            })
        }
    }

    private fun setJobHistoryAdapter() {
        var jobHistoryList = ArrayList<JobHistoryModel>()
        jobHistoryList.add(JobHistoryModel())
        jobHistoryList.add(JobHistoryModel())
        jobHistoryList.add(JobHistoryModel())
        jobHistoryList.add(JobHistoryModel())
        jobHistoryList.add(JobHistoryModel())

        var adapter = JobHistoryAdapter(this, jobHistoryList, object : JobHistoryAdapter.OnItemClickListener {
            override fun onClick(title: String) {
//                Toast.makeText(context, "clicked item $title", Toast.LENGTH_SHORT).show()
            }
        })
        jobHistoryRecyclerView?.adapter = adapter
        jobHistoryRecyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun setJobAdapter() {
        var jobList = ArrayList<JobModel>()
        jobList.add(JobModel())
        jobList.add(JobModel())
        jobList.add(JobModel())
        jobList.add(JobModel())
        jobList.add(JobModel())
        jobList.add(JobModel())

        var adapter = JobAdapter(this, jobList, object : JobAdapter.OnItemClickListener {
            override fun onClick(title: String) {
//                Toast.makeText(context, "clicked item $title", Toast.LENGTH_SHORT).show()
            }
        })
        jobRecyclerView?.adapter = adapter
        var layoutManager = CenterZoomLayoutManager(this, RecyclerView.HORIZONTAL, false)
        jobRecyclerView?.layoutManager = layoutManager
        jobRecyclerView?.smoothScrollBy(1, 0)
        GravitySnapHelper(Gravity.START).attachToRecyclerView(jobRecyclerView)
    }
}