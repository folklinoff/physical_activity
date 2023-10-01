package com.example.sportaandcharity.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sportaandcharity.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var progressView: TextView? = null
    private var seekbarStatusView: TextView? = null
    private var seekbarView: SeekBar? = null

    // SeekBar Range
    var MIN = 10
    var MAX = 160
    var STEP = 5

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvNumberOfSteps
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonJogging.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionNavHomeToMapsFragment()
            )
        }

        binding.buttonTraining.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionNavHomeToNavTraining()
            )
        }
             /*   // set max to seekbar
                seekbarView!!.max = (MAX - MIN) / STEP

                seekbarView!!.setOnSeekBarChangeListener(this)
*/

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                   fromUser: Boolean) {
        val progress_custom = MIN + ( progress * STEP )
        progressView!!.text = progress_custom.toString()
        seekbarStatusView!!.text = "Tracking Touch"
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        seekbarStatusView!!.text = "Started Tracking Touch"
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        seekbarStatusView!!.text = "Stopped Tracking Touch"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}