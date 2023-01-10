package com.example.teststrarwars.screen.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.teststrarwars.R
import com.example.teststrarwars.databinding.ItemLayuotBinding
import com.example.teststrarwars.data.models.People

class MainAdapter(
  private val listener: OnItemClickListener
): PagingDataAdapter<People, MainAdapter.MyViewHolder>(DiffUtilCallback) {



   inner class MyViewHolder(val binding: ItemLayuotBinding): RecyclerView.ViewHolder(binding.root){
    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position !=  RecyclerView.NO_POSITION){
                val item = getItem(position)
                if (item != null){
                    listener.onItemClick(item)
                }
            }
        }
    }

        fun bind(people: People){
            with(binding){
                itemName.text = people.name
            }
        }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayuotBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val people = getItem(position)

        if (people != null){
            holder.bind(people)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(people: People)
    }

    companion object DiffUtilCallback: DiffUtil.ItemCallback<People>(){
        override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem == newItem
        }

    }

}