package com.example.teststrarwars.screen.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teststrarwars.R
import com.example.teststrarwars.databinding.ItemLayuotBinding
import com.example.teststrarwars.models.PeopleItem
import com.example.teststrarwars.screen.PeopleItemListener

class FavoriteAdapter(
    private val listener: PeopleItemListener
): ListAdapter<PeopleItem, FavoriteAdapter.MyViewHolder>(DiffUtilCallbackFav), View.OnClickListener {

    override fun onClick(v: View) {
        val people = v.tag as PeopleItem
        when(v.id){
            R.id.img_my_favorite -> listener.peopleIsFavorite(people)
            else -> listener.peopleGo(people)
        }
    }

    class MyViewHolder(val binding: ItemLayuotBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayuotBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val people = getItem(position)

        with(holder.binding){
            root.tag = people

            itemName.text = people.name
        }
    }

    companion object DiffUtilCallbackFav: DiffUtil.ItemCallback<PeopleItem>(){
        override fun areItemsTheSame(oldItem: PeopleItem, newItem: PeopleItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: PeopleItem, newItem: PeopleItem): Boolean {
            return oldItem == newItem
        }

    }


}