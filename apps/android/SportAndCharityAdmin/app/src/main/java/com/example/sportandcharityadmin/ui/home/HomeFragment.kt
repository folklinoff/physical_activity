package com.example.sportandcharityadmin.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sportandcharityadmin.R
import com.example.sportandcharityadmin.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonRun.setOnClickListener {
            val popup = PopupMenu(requireActivity(), binding.buttonRun)
            popup.menuInflater
                .inflate(R.menu.popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                if(item.title.toString()!="0"){ binding.buttonRun.text = item.title.toString()}
                true
            }
            popup.show() //showing popup menu
        }

        binding.buttonByc.setOnClickListener {
            val popup = PopupMenu(requireActivity(), binding.buttonByc)
            popup.menuInflater
                .inflate(R.menu.popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                if(item.title.toString()!="0"){ binding.buttonByc.text = item.title.toString()}
                true
            }
            popup.show() //showing popup menu
        }

        binding.buttonCustom.setOnClickListener {
            val popup = PopupMenu(requireActivity(), binding.buttonCustom)
            popup.menuInflater
                .inflate(R.menu.popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                if(item.title.toString()!="0"){ binding.buttonCustom.text = item.title.toString()}
                true
            }
            popup.show() //showing popup menu
        }

        binding.buttonWalk.setOnClickListener {
            val popup = PopupMenu(requireActivity(), binding.buttonWalk)
            popup.menuInflater
                .inflate(R.menu.popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                if(item.title.toString()!="0"){ binding.buttonWalk.text = item.title.toString()}
                true
            }
            popup.show() //showing popup menu
        }

        binding.buttonSam.setOnClickListener {
            val popup = PopupMenu(requireActivity(), binding.buttonSam)
            popup.menuInflater
                .inflate(R.menu.popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                if(item.title.toString()!="0"){ binding.buttonSam.text = item.title.toString()}
                true
            }
            popup.show() //showing popup menu
        }

        binding.buttonSmt.setOnClickListener {
            val popup = PopupMenu(requireActivity(), binding.buttonSmt)
            popup.menuInflater
                .inflate(R.menu.popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                if(item.title.toString()!="0"){ binding.buttonSmt.text = item.title.toString()}
                true
            }
            popup.show() //showing popup menu
        }
        binding.button2.setOnClickListener {
            Toast.makeText(requireActivity(), "Изменения сохранены", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}