package com.example.teststrarwars.screen.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.teststrarwars.R
import com.example.teststrarwars.databinding.FragmentMainBinding
import com.example.teststrarwars.data.models.People
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), MainAdapter.OnItemClickListener{

    private var mBinding: FragmentMainBinding?= null
    private val binding get() = mBinding!!

    private val viewModel by viewModels<MainFragmentViewModel>()

    private val adapter by lazy { MainAdapter(this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentMainBinding.bind(view)

        binding.apply {
            rvMain.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PeopleLoadStateAdapter{adapter.retry()},
                footer = PeopleLoadStateAdapter{adapter.retry()}
            )
            btnTryAgain.setOnClickListener {
                adapter.retry()
            }
        }

        viewModel.people.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadStates ->
            binding.apply {
                progressBar.isVisible = loadStates.source.refresh is LoadState.Loading
                rvMain.isVisible = loadStates.source.refresh is LoadState.NotLoading
                btnTryAgain.isVisible = loadStates.source.refresh is LoadState.Error
                tvFailed.isVisible = loadStates.source.refresh is LoadState.Error

                if (loadStates.source.refresh is LoadState.NotLoading
                    && loadStates.append.endOfPaginationReached
                    && adapter.itemCount < 1){
                    rvMain.isVisible = false
                    tvNotFound.isVisible = true
                }else{
                    tvNotFound.isVisible = false
                }
            }
        }

        val menuHost: MenuHost =  requireActivity()
        menuHost.addMenuProvider(object: MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_search, menu)

                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (query != null){
                            binding.rvMain.scrollToPosition(0)
                            viewModel.searchPeople(query)
                            searchView.clearFocus()
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onItemClick(people: People) {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(people)
        findNavController().navigate(action)
    }


}