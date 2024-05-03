package com.example.mobilegame

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private val countryImages = arrayOf(
        R.drawable.afgan,
        R.drawable.auz,
        R.drawable.ban// Example image resource IDs for country flags

    )

    private val options = arrayOf(
        arrayOf("UK", "Afghanistan", "France", "Germany"),
        arrayOf("Canada", "UK", "Australia", "Japan"),
        arrayOf("Bangladesh", "France", "Spain", "Germany")
    )

    private val answers = arrayOf(
        1, // Answer for question 1 (index 1 corresponds to option B)
        2, // Answer for question 2 (index 1 corresponds to option B)
        0  // Answer for question 3 (index 0 corresponds to option A)
    )

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayQuestion()

        // Set click listeners for option buttons
        findViewById<Button>(R.id.option1Button).setOnClickListener { checkAnswer(0) }
        findViewById<Button>(R.id.option2Button).setOnClickListener { checkAnswer(1) }
        findViewById<Button>(R.id.option3Button).setOnClickListener { checkAnswer(2) }
        findViewById<Button>(R.id.option4Button).setOnClickListener { checkAnswer(3) }

        // Set click listener for the restart button
        findViewById<Button>(R.id.restartButton).setOnClickListener {
            Toast.makeText(this, "Lets start from beginning", Toast.LENGTH_SHORT).show()
            resetGame()
        }
    }

    private fun displayQuestion() {
        // Update question and options text
        findViewById<ImageView>(R.id.questionText).setImageResource(countryImages[currentQuestionIndex])
        findViewById<Button>(R.id.option1Button).text = options[currentQuestionIndex][0]
        findViewById<Button>(R.id.option2Button).text = options[currentQuestionIndex][1]
        findViewById<Button>(R.id.option3Button).text = options[currentQuestionIndex][2]
        findViewById<Button>(R.id.option4Button).text = options[currentQuestionIndex][3]

        // Reset button colors
        resetButtonColors()
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        // Check if the selected option is correct
        if (selectedOptionIndex == answers[currentQuestionIndex]) {
            // Increment score if correct
            score++
            // Update score text
            updateScoreText()
            // Change button color to green
            correctButtonColors(selectedOptionIndex)
        } else {
            // Change button color to red for incorrect answer
            wrongButtonColors(selectedOptionIndex)
        }

        // Move to the next question
        moveToNextQuestion()
    }

    private fun updateScoreText() {
        // Update the score text view with the current score
        val scoreText = "Score: $score"
        findViewById<TextView>(R.id.scoreText).text = scoreText
    }

    private fun resetButtonColors() {
        // Reset button colors to default
        val defaultColor = Color.BLUE
        findViewById<Button>(R.id.option1Button).setBackgroundColor(defaultColor)
        findViewById<Button>(R.id.option2Button).setBackgroundColor(defaultColor)
        findViewById<Button>(R.id.option3Button).setBackgroundColor(defaultColor)
        findViewById<Button>(R.id.option4Button).setBackgroundColor(defaultColor)
    }

    private fun correctButtonColors(buttonIndex: Int) {
        // Change button color to green for correct answer
        val greenColor = Color.GREEN
        when (buttonIndex) {
            0 -> findViewById<Button>(R.id.option1Button).setBackgroundColor(greenColor)
            1 -> findViewById<Button>(R.id.option2Button).setBackgroundColor(greenColor)
            2 -> findViewById<Button>(R.id.option3Button).setBackgroundColor(greenColor)
            3 -> findViewById<Button>(R.id.option4Button).setBackgroundColor(greenColor)
        }
    }

    private fun wrongButtonColors(buttonIndex: Int) {
        // Change button color to red for incorrect answer
        val redColor = Color.RED
        when (buttonIndex) {
            0 -> findViewById<Button>(R.id.option1Button).setBackgroundColor(redColor)
            1 -> findViewById<Button>(R.id.option2Button).setBackgroundColor(redColor)
            2 -> findViewById<Button>(R.id.option3Button).setBackgroundColor(redColor)
            3 -> findViewById<Button>(R.id.option4Button).setBackgroundColor(redColor)
        }
    }

    private fun moveToNextQuestion() {
        // Increment current question index
        currentQuestionIndex++

        // Check if all questions have been answered
        if (currentQuestionIndex < countryImages.size) {
            // Display next question after a delay
            handler.postDelayed({
                displayQuestion()
            }, 1000) // Delay of 1 second (1000 milliseconds)
        } else {
            // Show results when all questions are answered
            showResults()
        }
    }

    private fun resetGame() {
        // Reset current question index, score, and button colors
        currentQuestionIndex = 0
        score = 0
        resetButtonColors()
        updateScoreText()
        // Display from the the first question again
        setContentView(R.layout.activity_main)

        displayQuestion()

        // Set click listeners for option buttons
        findViewById<Button>(R.id.option1Button).setOnClickListener { checkAnswer(0) }
        findViewById<Button>(R.id.option2Button).setOnClickListener { checkAnswer(1) }
        findViewById<Button>(R.id.option3Button).setOnClickListener { checkAnswer(2) }
        findViewById<Button>(R.id.option4Button).setOnClickListener { checkAnswer(3) }
        // Set click listener for the restart button
        findViewById<Button>(R.id.restartButton).setOnClickListener {
            Toast.makeText(this, "Lets start from beginning", Toast.LENGTH_SHORT).show()
            resetGame()
        }

    }
    private fun saveHighestScore(score: Int) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("highestScore", score)
        editor.apply()
    }

    private fun showResults() {
        // Display the final score
        val finalScore = "Score: $score"
        findViewById<TextView>(R.id.scoreText).text = finalScore
        // Show a toast message
        Toast.makeText(this, finalScore, Toast.LENGTH_SHORT).show()

        // Check if the current score is higher than the saved highest score
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val highestScore = sharedPreferences.getInt("highestScore", 0)

        if (score > highestScore) {
            // Save the new highest score
            saveHighestScore(score)
        }

        // Check if all questions were answered correctly
        if (score == countryImages.size) {
            // Navigate to the userWon page
            setContentView(R.layout.activity_userwon)
            findViewById<TextView>(R.id.finalScoreTextView).text = finalScore
        } else {
            setContentView(R.layout.activity_userlost)
        }


    }
}