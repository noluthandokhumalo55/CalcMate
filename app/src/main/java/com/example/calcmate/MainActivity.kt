package com.example.calcmate

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput: String = ""
    private var lastOperation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // Set up button listeners
        setupButtonListeners()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupButtonListeners() {
        val buttons = listOf(
            R.id.button1 to "1",
            R.id.button2 to "2",
            R.id.button3 to "3",
            R.id.button4 to "4",
            R.id.button5 to "5",
            R.id.button6 to "6",
            R.id.button7 to "7",
            R.id.button8 to "8",
            R.id.button9 to "9",
            R.id.button0 to "0",
            R.id.buttonDecimal to "."
        )

        for ((buttonId, value) in buttons) {
            findViewById<Button>(buttonId).setOnClickListener {
                appendToInput(value)
            }
        }

        // Set up operation buttons
        findViewById<Button>(R.id.buttonAdd).setOnClickListener { performOperation("+") }
        findViewById<Button>(R.id.buttonSubtract).setOnClickListener { performOperation("-") }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { performOperation("*") }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { performOperation("/") }
        findViewById<Button>(R.id.buttonClear).setOnClickListener { clearInput() }
        findViewById<Button>(R.id.buttonEquals).setOnClickListener { calculateResult() }
        // Add listeners for scientific functions as needed
    }

    private fun appendToInput(value: String) {
        currentInput += value
        resultTextView.text = currentInput
    }

    private fun performOperation(operation: String) {
        if (currentInput.isNotEmpty()) {
            lastOperation = operation
            currentInput += operation
            resultTextView.text = currentInput
        }
    }

    private fun calculateResult() {
        try {
            val result = evaluateExpression(currentInput)
            resultTextView.text = result.toString()
            currentInput = result.toString() // Set result as current input for further calculations
        } catch (e: Exception) {
            resultTextView.text = "Error"
            currentInput = ""
        }
    }

    private fun clearInput() {
        currentInput = ""
        resultTextView.text = "0"
    }

    private fun evaluateExpression(expression: String): Double {
        // Simple expression evaluation (this is a naive implementation)
        // You might want to use a library or implement a proper parser for complex expressions
        return when {
            expression.contains("+") -> {
                val parts = expression.split("+")
                parts[0].toDouble() + parts[1].toDouble()
            }
            expression.contains("-") -> {
                val parts = expression.split("-")
                parts[0].toDouble() - parts[1].toDouble()
            }
            expression.contains("*") -> {
                val parts = expression.split("*")
                parts[0].toDouble() * parts[1].toDouble()
            }
            expression.contains("/") -> {
                val parts = expression.split("/")
                parts[0].toDouble() / parts[1].toDouble()
            }
            else -> expression.toDouble()
        }
    }
}