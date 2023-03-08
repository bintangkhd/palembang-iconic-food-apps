package com.example.palembangfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class AboutPageActivity : AppCompatActivity() {

    private lateinit var profilePict: ImageView
    private lateinit var profileName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_page)

        profilePict = findViewById(R.id.profile_image)
        profileName = findViewById(R.id.profile_name)
    }
}