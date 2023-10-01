package com.example.nfcapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sportaandcharity.R
import com.example.sportaandcharity.databinding.FragmentBinder

import com.example.sportaandcharity.nfcapp.Coroutines
import com.example.sportaandcharity.nfcapp.MActivity
import com.example.sportaandcharity.nfcapp.MViewModel

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MFragment : Fragment, CompoundButton.OnCheckedChangeListener {

    companion object {
        private val TAG : String = MFragment::class.java.getSimpleName()

        public fun newInstance() : MFragment = MFragment()
    }

    private var binder : FragmentBinder? = null
    private val viewModel : MViewModel by lazy { ViewModelProvider(this).get(MViewModel::class.java) }

    constructor() {

    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_m,container,false)
        binder?.setViewModel(viewModel)
        binder?.setLifecycleOwner(this@MFragment)
        return binder?.root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        Coroutines.main(this@MFragment) { scope ->
            scope.launch(block = {
                binder?.getViewModel()?.observeToast()?.collectLatest(action = { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                })
            })
            scope.launch(block = {
                binder?.getViewModel()?.observeTag()?.collectLatest(action = { tag ->
                    binder?.textViewExplanation?.setText(tag)
                })
            })
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCheckedChanged(buttonView : CompoundButton?, isChecked : Boolean) {
        if (buttonView == binder?.toggleButton)
            viewModel.onCheckNFC(isChecked)
    }
}