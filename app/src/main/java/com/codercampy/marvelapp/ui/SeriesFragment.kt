package com.codercampy.marvelapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.codercampy.marvelapp.databinding.FragmentSeriesBinding

class SeriesFragment:Fragment() {

    private lateinit var binding: FragmentSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(layoutInflater)
        return binding.root
    }


    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = SeriesFragmentArgs.fromBundle(requireArguments()).item


        Glide.with(binding.imageSeries).load(item.thumbnail.getUrl()).into(binding.imageSeries)

        binding.tvSeriesName.text = item.name

        binding.tvDescriptionSeriesDetails.text = item.description






        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        })


    }
}