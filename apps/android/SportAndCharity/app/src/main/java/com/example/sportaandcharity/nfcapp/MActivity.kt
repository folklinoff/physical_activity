package com.example.sportaandcharity.nfcapp

import android.nfc.NfcAdapter
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.nfcapp.MFragment
import com.example.sportaandcharity.R
import com.example.sportaandcharity.databinding.ActivityBinder
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

public class MActivity : AppCompatActivity, CompoundButton.OnCheckedChangeListener, NfcAdapter.ReaderCallback {

    companion object {
        private val TAG = MActivity::class.java.getSimpleName()
    }

    private var binder : ActivityBinder? = null
    private val viewModel : MViewModel by lazy { ViewModelProvider(this).get(MViewModel::class.java) }

    constructor() {

    }

    override fun onCreate(savedInstanceState : Bundle?) {
        binder = DataBindingUtil.setContentView(this@MActivity, R.layout.activity_m)
        binder?.setViewModel(viewModel)
        binder?.setLifecycleOwner(this@MActivity)
        super.onCreate(savedInstanceState)
        binder?.toggleButton?.setOnCheckedChangeListener(this@MActivity)
        Coroutines.main(this@MActivity) { scope ->
            scope.launch(block = {
                binder?.getViewModel()?.observeNFCStatus()?.collectLatest(action = { status ->
                    Log.d(TAG, "observeNFCStatus $status")
                    if (status == NFCStatus.NoOperation) NFCManager.disableReaderMode(
                        this@MActivity,
                        this@MActivity
                    )
                    else if (status == NFCStatus.Tap) NFCManager.enableReaderMode(
                        this@MActivity,
                        this@MActivity,
                        this@MActivity,
                        viewModel.getNFCFlags(),
                        viewModel.getExtras()
                    )
                })
            })
            scope.launch(block = {
                binder?.getViewModel()?.observeToast()?.collectLatest(action = { message ->
                    Log.d(TAG, "observeToast $message")
                    Toast.makeText(this@MActivity, message, Toast.LENGTH_LONG).show()
                })
            })
            scope.launch(block = {
                binder?.getViewModel()?.observeTag()?.collectLatest(action = { tag ->
                    Log.d(TAG, "observeTag $tag")
                    binder?.textViewExplanation?.setText(tag)
                })
            })
        }
    }

    override fun onCheckedChanged(buttonView : CompoundButton?, isChecked : Boolean) {
        if (buttonView == binder?.toggleButton)
            viewModel.onCheckNFC(isChecked)
    }

    override fun onTagDiscovered(tag : Tag?) {
        binder?.getViewModel()?.readTag(tag)
    }

    private fun launchMainFragment() {
        if (getSupportFragmentManager().findFragmentByTag(MFragment::class.java.getSimpleName()) == null)
            getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, MFragment.newInstance(), MFragment::class.java.getSimpleName())
                .addToBackStack(MFragment::class.java.getSimpleName())
                .commit()
    }
}