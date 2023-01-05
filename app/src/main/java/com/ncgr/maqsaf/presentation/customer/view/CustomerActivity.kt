package com.ncgr.maqsaf.presentation.customer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ncgr.maqsaf.databinding.ActivityCustomerBinding
import com.ncgr.maqsaf.presentation.customer.adapter.ItemListAdapter
import com.ncgr.maqsaf.presentation.customer.viewModel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerActivity : AppCompatActivity() {

    private var binding: ActivityCustomerBinding? = null
    private val viewModel : CustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {
            lifecycleOwner = this@CustomerActivity
            itemRecyclerView.adapter = ItemListAdapter()
            viewModel = this@CustomerActivity.viewModel
        }
    }


}