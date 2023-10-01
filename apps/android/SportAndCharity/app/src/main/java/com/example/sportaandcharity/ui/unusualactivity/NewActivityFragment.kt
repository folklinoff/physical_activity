package com.example.sportaandcharity.ui.unusualactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sportaandcharity.databinding.ActivityMainBinding
import com.example.sportaandcharity.databinding.FragmentNewActivityBinding


class NewActivityFragment : Fragment() {

    companion object {
        fun newInstance() = NewActivityFragment()
    }

    private lateinit var binding: FragmentNewActivityBinding
    private lateinit var viewModel: NewActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewActivityViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button4.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW, Uri.parse(
                    "content://media/internal/images/media"
                )
            )
            startActivity(intent)
        }
    }

}