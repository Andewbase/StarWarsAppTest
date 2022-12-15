package com.example.teststrarwars.screen.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teststrarwars.util.MAIN
import com.example.teststrarwars.R
import com.example.teststrarwars.databinding.FragmentMainBinding
import com.example.teststrarwars.models.PeopleItem
import com.example.teststrarwars.screen.PeopleItemListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), PeopleItemListener {

    private var mBinding: FragmentMainBinding?= null
    private val binding get() = mBinding!!

    lateinit var recyclerView: RecyclerView

    private val viewModel by viewModels<MainFragmentViewModel>()

    private val adapter by lazy { MainAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        recyclerView = binding.rvMain
        recyclerView.adapter = adapter

        try {
            viewModel.getPeopleRetrofit()
            viewModel.myPeople.observe(viewLifecycleOwner) { list ->
                adapter.submitList(list.results)
            }
        }catch (e: Exception){
            Toast.makeText(MAIN, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    override fun peopleGo(peopleItem: PeopleItem) {
        val directions = MainFragmentDirections.actionMainFragmentToDetailFragment(peopleItem)
        findNavController().navigate(directions)
    }

    override fun peopleIsFavorite(peopleItem: PeopleItem) {
      //TODO
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_favorite -> {
                val action = MainFragmentDirections.actionMainFragmentToFavoriteFragment()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }





}