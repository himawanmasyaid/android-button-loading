package com.himawanmasyaid.androidbuttonloading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.himawanmasyaid.androidbuttonloading.databinding.ActivityMainBinding
import com.himawanmasyaid.button_loading.InternetConnection
import com.himawanmasyaid.button_loading.InternetConnection.isInternetConnected

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            if (InternetConnection.isInternetConnected(this@MainActivity)) {
                toast("Connected")
            } else {
                toast("Disconnect")
            }
        }

    }

    fun toast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

}