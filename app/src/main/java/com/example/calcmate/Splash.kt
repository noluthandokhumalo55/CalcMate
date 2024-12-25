package com.example.calcmate

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Ensure this matches your layout file name

        val imageView = findViewById<ImageView>(R.id.imageView)

        // Create a jumping animation
        val jumpAnimation = ObjectAnimator.ofFloat(imageView, "translationY", 0f, -100f, 0f)
        jumpAnimation.duration = 1000 // 1 second for the jump
        jumpAnimation.repeatCount = ObjectAnimator.INFINITE // Repeat indefinitely

        // Start the animation
        jumpAnimation.start()

        // Delay for 3 seconds before starting the MainActivity
        Handler().postDelayed({
            // Stop the animation
            jumpAnimation.cancel()
            // Start MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Close the Splash activity
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}