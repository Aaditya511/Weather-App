package com.demo.weather

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.demo.weather.databinding.ActivitySplashBinding
import com.demo.weather.ui.current.CurrentViewModel
import com.demo.weather.viewmodel.CommonViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val viewModel = ViewModelProvider(this).get(CommonViewModel::class.java)
            val userName = viewModel.getUsername(this)

            if (userName.isBlank()) {
                startActivity(Intent(this, EnterNameActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()

        }, 1000)
    }

}