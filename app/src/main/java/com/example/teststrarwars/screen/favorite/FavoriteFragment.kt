package com.example.teststrarwars.screen.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teststrarwars.databinding.FragmentFavoriteBinding
import com.example.teststrarwars.models.PeopleItem
import com.example.teststrarwars.screen.PeopleItemListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(), PeopleItemListener {
    private var mBinding: FragmentFavoriteBinding?= null
    private val binding get() = mBinding!!

    lateinit var recyclerView: RecyclerView

    private val viewModel by viewModels<FavoriteFragmentViewModel>()

    private val adapter by lazy { FavoriteAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        recyclerView = binding.rvFavorite
        recyclerView.adapter = adapter

        viewModel.getAllPeople().observe(viewLifecycleOwner) { list ->
            adapter.submitList(list.asReversed())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun peopleGo(peopleItem: PeopleItem) {
        val directions = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(peopleItem)
        findNavController().navigate(directions)
    }

    override fun peopleIsFavorite(peopleItem: PeopleItem) {
        TODO("Not yet implemented")
    }
}