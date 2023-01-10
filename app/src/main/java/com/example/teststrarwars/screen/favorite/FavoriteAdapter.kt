package com.example.teststrarwars.screen.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teststrarwars.R
import com.example.teststrarwars.data.local.FavoritePeople
import com.example.teststrarwars.databinding.ItemLayuotBinding
import com.example.teststrarwars.data.models.People

class FavoriteAdapter: ListAdapter<FavoritePeople, FavoriteAdapter.FavoriteViewHolder>(DiffUtilCallbackFav) {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

   inner class FavoriteViewHolder(val binding: ItemLayuotBinding): RecyclerView.ViewHolder(binding.root){

       fun bind(favoritePeople: FavoritePeople){
            with(binding){
                itemName.text = favoritePeople.name
                binding.root.setOnClickListener { onItemClickCallback?.onItemClick(favoritePeople) }
            }
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayuotBinding.inflate(inflater, parent, false)

        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val people = getItem(position)

       holder.bind(people)
    }

    interface OnItemClickCallback {
        fun onItemClick(favoritePeople: FavoritePeople)
    }

    companion object DiffUtilCallbackFav: DiffUtil.ItemCallback<FavoritePeople>(){
        override fun areItemsTheSame(oldItem: FavoritePeople, newItem: FavoritePeople): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: FavoritePeople, newItem: FavoritePeople): Boolean {
            return oldItem == newItem
        }

    }


}