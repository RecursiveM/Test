package com.ncgr.maqsaf.presentation.home.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
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

    fun showPromptDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Title")

        // Set up the input
        val input = EditText(this)
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.hint = "Enter Password"
        input.inputType = InputType.TYPE_CLASS_NUMBER
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK") { dialog, which ->
            // Here you get get input text from the Edittext
            var m_Text = input.text.toString()
            if (m_Text == "123456"){
                startActivity(Intent(this, SellerActivity::class.java))
            }else{
                val errorDialog: AlertDialog.Builder = AlertDialog.Builder(this)
                errorDialog.setTitle("Wrong Password").show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}