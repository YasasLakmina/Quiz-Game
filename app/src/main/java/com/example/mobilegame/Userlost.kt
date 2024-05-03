import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobilegame.HighestScore
import com.example.mobilegame.MainActivity
import com.example.mobilegame.R

class Userlost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlost)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set click listener for the "Try Again" button
        findViewById<Button>(R.id.tryAgainButton).setOnClickListener {
            // Restart the game
            Toast.makeText(this, "Restarting the game...", Toast.LENGTH_SHORT).show()

        }


    }
}
