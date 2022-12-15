package com.example.teststrarwars.screen.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.teststrarwars.util.MAIN
import com.example.teststrarwars.R
import com.example.teststrarwars.util.SaveShared
import com.example.teststrarwars.databinding.FragmentDetailsBinding
import com.example.teststrarwars.models.PeopleItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var mBinding: FragmentDetailsBinding? = null
    private val binding get() = mBinding!!

    private val viewModel by viewModels<DetailsFragmentViewModels>()
    private val safeArgs: DetailFragmentArgs by navArgs()

    private var isFavorite = false

    private lateinit var peopleItem: PeopleItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        peopleItem = safeArgs.people

        binding.tvName.text = peopleItem.name
        binding.tvDate.text = peopleItem.birth_year
        binding.tvGender.text = peopleItem.gender
        binding.tvHomeWrold.text = peopleItem.homeworld

        val valueBool = SaveShared.getFavorite(MAIN, peopleItem.id.toString())

        if (isFavorite != valueBool){
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }



        binding.imgDetailFavorite.setOnClickListener {
            if (isFavorite == valueBool){

                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)

                SaveShared.setFavorite(MAIN, peopleItem.id.toString(), true)

                viewModel.insert(peopleItem){}

                isFavorite = true
            }else{
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)

                viewModel.delete(peopleItem){}

                SaveShared.setFavorite(MAIN, peopleItem.id.toString(), false)

                isFavorite = false
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}