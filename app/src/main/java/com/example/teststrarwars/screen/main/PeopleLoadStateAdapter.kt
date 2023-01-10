package com.example.teststrarwars.screen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teststrarwars.databinding.PeopleLoadStateFooterBinding

class PeopleLoadStateAdapter(private val retry: ()-> Unit): LoadStateAdapter<PeopleLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: PeopleLoadStateFooterBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.btnRetry.setOnClickListener{
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState){
            with(binding){
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                tvError.isVisible = loadState !is LoadState.Loading
            }
        }

    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = PeopleLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

}