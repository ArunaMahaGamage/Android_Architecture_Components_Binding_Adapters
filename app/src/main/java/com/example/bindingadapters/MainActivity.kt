package com.example.bindingadapters

import android.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bindingadapters.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        // Use data bonding to one of below two
//        val bindings: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bind Models
        var user = User.instance
        binding.user = user

        // Bind Events
        binding.handlers = MyHandlers()

        user.firstName.set("Aruna")
        user.imageUrl.set("https://avatars.githubusercontent.com/u/25635071?s=400&u=1de9ce133f26b8c9d6605da4cba5eb5c3d444923&v=4")

    }
}