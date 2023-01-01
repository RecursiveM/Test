package com.ncgr.maqsaf.presentation.seller.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ncgr.maqsaf.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)
    }
}