package com.example.mobilegame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HighestScore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_highest_score)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Retrieve the highest score from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val highestScore = sharedPreferences.getInt("highestScore", 0)

        // Set the highest score in the highestScoreTextView
        findViewById<TextView>(R.id.highestScoreTextView).text = "Highest Score: $highestScore"

        findViewById<Button>(R.id.backToMainMenuButton).setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }
    }
}