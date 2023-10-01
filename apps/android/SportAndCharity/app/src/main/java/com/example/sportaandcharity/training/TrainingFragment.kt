package com.example.sportaandcharity.training


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sportaandcharity.R

import com.example.sportaandcharity.databinding.FragmentTrainingBinding
import com.example.sportaandcharity.ui.home.HomeFragmentDirections


class TrainingFragment : Fragment() {

    private lateinit var binding: FragmentTrainingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val path = "android.resource://" + requireActivity()!!.packageName + "/" + R.raw.vid
        binding.videoView.setVideoURI(Uri.parse(path))
        binding.videoView.start()

        binding.button5.setOnClickListener {
            findNavController().navigate(
                TrainingFragmentDirections.actionNavTrainingToNavHome()
            )
        }
    }
}