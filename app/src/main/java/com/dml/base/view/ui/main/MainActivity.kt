package com.dml.base.view.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseActivity
import com.dml.base.network.model.JobHistoryResponse
import com.dml.base.network.model.JobResponse
import com.dml.base.view.adapter.JobAdapter
import com.dml.base.view.adapter.JobHistoryAdapter
import com.dml.base.view.custom.CenterZoomLayoutManager
import com.dml.base.view.ui.settings.SettingsActivity
import com.dml.base.view.ui.wallet.WalletActivity
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
        var jobHistoryList = ArrayList<JobHistoryResponse>()
        jobHistoryList.add(JobHistoryResponse())
        jobHistoryList.add(JobHistoryResponse())
        jobHistoryList.add(JobHistoryResponse())
        jobHistoryList.add(JobHistoryResponse())
        jobHistoryList.add(JobHistoryResponse())

        var adapter = JobHistoryAdapter(this, jobHistoryList, object : JobHistoryAdapter.OnItemClickListener {
            override fun onClick(title: String) {
//                Toast.makeText(context, "clicked item $title", Toast.LENGTH_SHORT).show()
            }
        })
        jobHistoryRecyclerView?.adapter = adapter
        jobHistoryRecyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun setJobAdapter() {
        val itemWidth = Utility.getScreenWidth() * 7 / 8

        var jobList = ArrayList<JobResponse>()
        jobList.add(JobResponse())
        jobList.add(JobResponse())
        jobList.add(JobResponse())
        jobList.add(JobResponse())
        jobList.add(JobResponse())
        jobList.add(JobResponse())

        var adapter = JobAdapter(this, itemWidth, jobList, object : JobAdapter.OnItemClickListener {
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