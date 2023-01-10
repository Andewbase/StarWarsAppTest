package com.example.teststrarwars.screen.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.teststrarwars.R
import com.example.teststrarwars.databinding.FragmentDetailsBinding
import com.example.teststrarwars.data.models.People
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_details) {

    private var mBinding: FragmentDetailsBinding? = null
    private val binding get() = mBinding!!

    private val viewModel by viewModels<DetailsFragmentViewModels>()

    private val safeArgs: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val people = safeArgs.people

            var _isChecked = false
            CoroutineScope(Dispatchers.IO).launch {
                val count = viewModel.checkPeople(people.id!!)
                withContext(Dispatchers.Main){
                    if (count > 0){
                        imgDetailFavorite.isChecked = true
                        _isChecked = true
                    }else{
                        imgDetailFavorite.isChecked = false
                        _isChecked = false
                    }
                }
            }

            tvName.text = people.name
            tvDate.text = people.birth_year
            tvGender.text = people.gender
            tvHomeWrold.text = people.homeworld

            imgDetailFavorite.setOnClickListener {
                _isChecked = !_isChecked
                if (_isChecked){
                    viewModel.addToFavorite(people)
                }else{
                    viewModel.removeFavoritePeople(people.id!!)
                }
                imgDetailFavorite.isChecked = _isChecked
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}