package com.codercampy.marvelapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.codercampy.firebaseclass.MessageAdapter
import com.codercampy.marvelapp.databinding.FragmentChatBinding
import com.codercampy.marvelapp.model.ChatModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var adapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MessageAdapter()
        binding.recyclerView.adapter = adapter

        binding.btnSend.setOnClickListener {
            sendMessage()
        }


        Firebase.firestore.collection("chats")
            .orderBy("timestamp",com.google.firebase.firestore.Query.Direction.ASCENDING)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.e("REALTIME", "Listen failed.", e)
                    return@addSnapshotListener
                }

                val chats = mutableListOf<ChatModel>()
                for (doc in value!!.documentChanges) {
                    if (doc.type == com.google.firebase.firestore.DocumentChange.Type.ADDED) {
                        val chat = doc.document.toObject(ChatModel::class.java)
                        chats.add(chat)
                    }
                }
                Log.e("adapter.itemCount", adapter.itemCount.toString())
                adapter.addChats(chats)
                binding.recyclerView.postDelayed({
                    Log.e("adapter.itemCount", adapter.itemCount.toString())
                    binding.recyclerView.scrollToPosition(adapter.itemCount - 1)
                }, 100)
            }


    }

    private fun sendMessage() {
        val user = Firebase.auth.currentUser ?: return
        val text = binding.etChat.text?.toString()?.trim()
        if (text.isNullOrEmpty()) {
            return
        }

        val chat = ChatModel(
            System.currentTimeMillis().toString(),
            System.currentTimeMillis(),
            text,
            buildMap {
                put("id", user.uid)
                put("name", user.displayName)
            }
        )

        Firebase.firestore.collection("chats")
            .add(chat)
            .addOnSuccessListener { documentReference ->
                binding.etChat.text = null
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Unable to send message", Toast.LENGTH_SHORT)
                    .show()
            }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        })

    }



}