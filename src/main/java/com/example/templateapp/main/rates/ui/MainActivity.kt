package com.example.templateapp.main.rates.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.templateapp.R
import com.example.templateapp.core.BaseActivity
import com.example.templateapp.core.provider.DialogManager
import com.example.templateapp.databinding.ActivityMainBinding
import com.example.templateapp.main.rates.vm.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    private val dialogManager: DialogManager by inject { parametersOf(this as BaseActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.executePendingBindings()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.fetchRates("", 100.0)

        viewModel.loading.observe(this, Observer { isLoading ->
            if (isLoading != null && isLoading) {
                showLoadingLayout()
            } else {
                hideLoadingLayout()
            }
        })

        viewModel.showDialog.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { dialog ->
                if (dialog.titleText != "InvalidMac") {
                    dialogManager.showDialog(dialog)
                }
            }
        })
    }
}
