import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobilegame.HighestScore
import com.example.mobilegame.MainActivity
import com.example.mobilegame.R

class Userwon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_userwon)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve the final score from the intent
        val finalScore = intent.getStringExtra("finalScore")
        // Display the final score
        findViewById<TextView>(R.id.finalScoreTextView).text = finalScore

        // Find the replay button
        val replayButton = findViewById<Button>(R.id.replayButton)
        // Set click listener to the replay button
        replayButton.setOnClickListener {
            // Create an intent to start the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            // Start the MainActivity
            startActivity(intent)
            // Finish this activity to remove it from the back stack
            finish()
        }

    }
}
