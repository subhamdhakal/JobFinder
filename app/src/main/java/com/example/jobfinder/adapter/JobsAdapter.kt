package com.example.jobfinder.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jobfinder.R
import com.example.jobfinder.data.JobGitHub
import com.example.jobfinder.databinding.ItemJobBinding
import com.example.jobfinder.viewmodel.ItemJobViewModel
import com.squareup.picasso.Picasso

class JobsAdapter(var context: Context) : RecyclerView.Adapter<JobsAdapter.JobsAdapterViewHolder>() {
    var jobGitHubs: List<JobGitHub> = ArrayList()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): JobsAdapterViewHolder {
        var itemJobBinding: ItemJobBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_job, p0, false)
        return JobsAdapterViewHolder(itemJobBinding)
    }

    fun setData(jobGitHubs: List<JobGitHub>) {
        this.jobGitHubs = jobGitHubs
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return jobGitHubs.size
    }

    override fun onBindViewHolder(p0: JobsAdapterViewHolder, p1: Int) {
        p0.bind(jobGitHubs[p1])
    }

    inner class JobsAdapterViewHolder(var binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root) {
        var viewModel: ItemJobViewModel = ItemJobViewModel()

        init {
            binding.viewModel = viewModel
        }

        fun bind(item: JobGitHub) {
            viewModel.gitHub = item
                Picasso.get().load(item?.company_logo)
                    .resize(50, 50)
                    .placeholder(R.drawable.ic_factory)
                    .into(binding.imageViewCompanyLogo)
            binding.viewModel = viewModel
        }

    }
}