package com.example.mobilegame


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_menu)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Set click listener for the "View Highest Score" button
        findViewById<Button>(R.id.highestScoreButton).setOnClickListener {
            val intent = Intent(this, HighestScore::class.java)
            startActivity(intent)
        }
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            // Start the game activity
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        // Set click listener for the "View Highest Score" button
        findViewById<Button>(R.id.highestScoreButton).setOnClickListener {
            val intent = Intent(this, HighestScore::class.java)
            startActivity(intent)
        }
    }
}