package com.codercampy.marvelapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codercampy.marvelapp.Utilities.PRIVATE_KEY
import com.codercampy.marvelapp.Utilities.PUBLIC_KEY
import com.codercampy.marvelapp.adapter.CharactersAdapter
import com.codercampy.marvelapp.api.ApiSource
import com.codercampy.marvelapp.databinding.FragmentCharactersBinding
import com.codercampy.marvelapp.Utilities.md5
import com.codercampy.marvelapp.model.BaseResponse
import com.codercampy.marvelapp.Utilities.showShortToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var adapter: CharactersAdapter

    private var limit: Int = 51
    private var offset = 0
    private var isFetching = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivAccountIcon.setOnClickListener{
            findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToProfileFragment()
            )
    }

        adapter = CharactersAdapter()
        binding.recyclerView.adapter = adapter

        adapter.setListener{
            findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToSpecificCharacterFragment(it)
            )
        }
        fetchCharactersAndShow()


        val lm = binding.recyclerView.layoutManager as GridLayoutManager
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val pos = lm.findLastCompletelyVisibleItemPosition()
                if (dy > 0 && !isFetching && pos > (lm.itemCount - 20)) {
                    offset  += limit
                    fetchCharactersAndShow()
                }

            }

        })

    }

    private fun fetchCharactersAndShow() {
        isFetching = true
        val ts = System.currentTimeMillis()
        val hash = "$ts$PRIVATE_KEY$PUBLIC_KEY".md5()
        binding.progress.visibility = View.VISIBLE
        ApiSource.marvelApi.getCharacters(ts, PUBLIC_KEY, hash, offset, limit)
            .enqueue(object : Callback<BaseResponse?> {
                override fun onResponse(p0: Call<BaseResponse?>, p1: Response<BaseResponse?>) {
                    val res = p1.body()?.data
                    if (res != null) {
                        adapter.setCharacters(res.results)
                    }
                    isFetching = false
                    binding.progress.visibility = View.GONE
                }

                override fun onFailure(p0: Call<BaseResponse?>, p1: Throwable) {
                    Log.e("onFailure", "onFailure", p1)
                    requireContext().showShortToast(p1.message)
                    isFetching = false
                    binding.progress.visibility = View.GONE
                }
            })
    }

}

