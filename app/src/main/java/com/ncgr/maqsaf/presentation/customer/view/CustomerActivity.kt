package com.ncgr.maqsaf.presentation.customer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ncgr.maqsaf.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
    }
}