package com.codercampy.marvelapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.codercampy.marvelapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.userProfileChangeRequest

class ProfileFragment:Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivFanClub.setOnClickListener{
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToChatFragment())
        }

        binding.tvFanClub.setOnClickListener{
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToChatFragment())
        }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        })

    }
}