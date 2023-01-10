package com.example.teststrarwars.screen.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teststrarwars.R
import com.example.teststrarwars.data.local.FavoritePeople
import com.example.teststrarwars.databinding.FragmentFavoriteBinding
import com.example.teststrarwars.data.models.People
import com.example.teststrarwars.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var mBinding: FragmentFavoriteBinding?= null
    private val binding get() = mBinding!!

    private val viewModel by viewModels<FavoriteFragmentViewModel>()

    private val adapter by lazy { FavoriteAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentFavoriteBinding.bind(view)

        viewModel.people.observe(viewLifecycleOwner) {
            adapter.submitList(it.asReversed())
            binding.apply {
                rvFavorite.setHasFixedSize(true)
                rvFavorite.adapter = adapter
            }
        }

        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback{
            override fun onItemClick(favoritePeople: FavoritePeople) {

                val people = favoritePeople.toPeople()

                val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(people)
                findNavController().navigate(action)
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

}