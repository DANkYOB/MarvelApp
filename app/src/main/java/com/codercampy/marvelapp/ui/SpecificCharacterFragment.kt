package com.codercampy.marvelapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.codercampy.marvelapp.PRIVATE_KEY
import com.codercampy.marvelapp.PUBLIC_KEY
import com.codercampy.marvelapp.adapter.CharactersAdapter
import com.codercampy.marvelapp.adapter.SpecificAdapter
import com.codercampy.marvelapp.api.ApiSource
import com.codercampy.marvelapp.databinding.FragmentSpecificCharacterBinding
import com.codercampy.marvelapp.md5
import com.codercampy.marvelapp.model.BaseResponse
import com.codercampy.marvelapp.model.ComicsResponse
import com.codercampy.marvelapp.model.ItemModel
import com.codercampy.marvelapp.model.SeriesResponse
import com.codercampy.marvelapp.model.Thumbnail
import com.codercampy.marvelapp.showShortToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpecificCharacterFragment: Fragment () {

    private lateinit var binding: FragmentSpecificCharacterBinding
    private var limit: Int = 51
    private var offset = 0
    private var isFetching = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpecificCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val item = SpecificCharacterFragmentArgs.fromBundle(requireArguments()).item

        Glide.with(binding.imageSpecific).load(item.thumbnail.getUrl()).into(binding.imageSpecific)

        binding.tvSpecificChar.text = item.name




        val ts = System.currentTimeMillis()
        val hash = "$ts$PRIVATE_KEY$PUBLIC_KEY".md5()

        val adapterComics = SpecificAdapter()
        binding.rvComics.adapter = adapterComics


        adapterComics.setListener{
            findNavController().navigate(SpecificCharacterFragmentDirections.actionSpecificCharacterFragmentToComicFragment())
        }




        val adapterSeries = SpecificAdapter()
        binding.rvSeries.adapter = adapterSeries






        ApiSource.comicsApi.getComics(item.id, ts, PUBLIC_KEY,hash,offset,limit)
            .enqueue(object : Callback<ComicsResponse?> {
            override fun onResponse(p0: Call<ComicsResponse?>, p1: Response<ComicsResponse?>) {
                val res = p1.body()?.data?.results
                if (res != null) {
                    adapterComics.setSpecific(res)
                }

            }

            override fun onFailure(p0: Call<ComicsResponse?>, p1: Throwable) {
                Log.e("onFailure", "onFailure", p1)
                requireContext().showShortToast(p1.message)
                isFetching = false
            }


        })


        ApiSource.seriesApi.getSeries(item.id, ts, PUBLIC_KEY,hash,offset,limit)
            .enqueue(object : Callback<SeriesResponse?> {
                override fun onResponse(p0: Call<SeriesResponse?>, p1: Response<SeriesResponse?>) {
                    val res = p1.body()?.data?.results
                    if (res != null) {
                        adapterSeries.setSpecific(res)
                    }

                }

                override fun onFailure(p0: Call<SeriesResponse?>, p1: Throwable) {
                    Log.e("onFailure", "onFailure", p1)
                    requireContext().showShortToast(p1.message)
                    isFetching = false
                }


            })


    }
}

