package com.ncgr.maqsaf.presentation.home.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.databinding.ActivityHomeBinding
import com.ncgr.maqsaf.presentation.customer.view.CustomerActivity
import com.ncgr.maqsaf.presentation.seller.view.SellerActivity
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

    fun showAlertDialog(){
        val alertDialog = AlertDialog.Builder(this).create()
        val alertView = layoutInflater.inflate(R.layout.alert_dialog,null)
        val cancelButton : Button = alertView.findViewById(R.id.dialog_dismiss_button)
        val confirmButton : Button = alertView.findViewById(R.id.dialog_confirm_button)
        val passwordText : EditText = alertView.findViewById(R.id.password_text)

        alertDialog.setView(alertView)
        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }
        confirmButton.setOnClickListener {
            if (passwordText.text.toString() == "123456"){
                alertDialog.dismiss()
                startActivity(Intent(this, SellerActivity::class.java))
            }
            else {
                alertDialog.dismiss()
               AlertDialog.Builder(this).setTitle("Wrong Password").show()
            }
        }
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}