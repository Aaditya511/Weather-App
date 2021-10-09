package com.demo.weather

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.demo.weather.databinding.ActivityEnterNameBinding
import com.demo.weather.viewmodel.CommonViewModel

class EnterNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnterNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {

            val name = binding.edtUserName.text.toString()
            if (name.isNotBlank()) {
                val viewModel = ViewModelProvider(this).get(CommonViewModel::class.java)
                viewModel.saveUserName(this, name)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please enter valid name", Toast.LENGTH_SHORT).show()
            }


        }

    }

}