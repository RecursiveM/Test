package com.ncgr.maqsaf.presentation.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ncgr.maqsaf.databinding.ActivityHomeBinding
import com.ncgr.maqsaf.presentation.customer.view.CustomerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {
            lifecycleOwner = this@HomeActivity
            homeActivity = this@HomeActivity
        }
    }

    fun navigateToCustomerActivity(){
        startActivity(Intent(this, CustomerActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}