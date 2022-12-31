package com.ncgr.maqsaf.presentation.home.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.databinding.ActivityHomeBinding
import com.ncgr.maqsaf.presentation.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {
            lifecycleOwner = this@HomeActivity
            viewModel = this@HomeActivity.viewModel
        }
    }
}