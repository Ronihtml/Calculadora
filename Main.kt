package com.example.aplicativosorteio

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private var firstNumber = ""
    private var currentOperation = ""
    private var newNumber = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        // Números
        findViewById<Button>(R.id.btn0).setOnClickListener { appendNumber("0") }
        findViewById<Button>(R.id.btn1).setOnClickListener { appendNumber("1") }
        findViewById<Button>(R.id.btn2).setOnClickListener { appendNumber("2") }
        findViewById<Button>(R.id.btn3).setOnClickListener { appendNumber("3") }
        findViewById<Button>(R.id.btn4).setOnClickListener { appendNumber("4") }
        findViewById<Button>(R.id.btn5).setOnClickListener { appendNumber("5") }
        findViewById<Button>(R.id.btn6).setOnClickListener { appendNumber("6") }
        findViewById<Button>(R.id.btn7).setOnClickListener { appendNumber("7") }
        findViewById<Button>(R.id.btn8).setOnClickListener { appendNumber("8") }
        findViewById<Button>(R.id.btn9).setOnClickListener { appendNumber("9") }
        findViewById<Button>(R.id.btnDot).setOnClickListener { appendNumber(".") }

        // Operações
        findViewById<Button>(R.id.btnPlus).setOnClickListener { setOperation("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { setOperation("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperation("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperation("÷") }
        findViewById<Button>(R.id.btnPercent).setOnClickListener { calculatePercentage() }
        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearCalculator() }
    }

    @SuppressLint("SetTextI18n")
    private fun appendNumber(number: String) {
        if (newNumber) {
            tvResult.text = number
            newNumber = false
        } else {
            if (number == "." && tvResult.text.contains(".")) return
            tvResult.text = "${tvResult.text}$number"
        }
    }

    private fun setOperation(operation: String) {
        firstNumber = tvResult.text.toString()
        currentOperation = operation
        newNumber = true
    }

    @SuppressLint("SetTextI18n")
    private fun calculateResult() {
        if (firstNumber.isEmpty() || currentOperation.isEmpty()) return

        val secondNumber = tvResult.text.toString()
        var result = 0.0

        try {
            result = when (currentOperation) {
                "+" -> firstNumber.toDouble() + secondNumber.toDouble()
                "-" -> firstNumber.toDouble() - secondNumber.toDouble()
                "×" -> firstNumber.toDouble() * secondNumber.toDouble()
                "÷" -> firstNumber.toDouble() / secondNumber.toDouble()
                else -> return
            }

            tvResult.text = formatResult(result)
            firstNumber = ""
            currentOperation = ""
            newNumber = true
        } catch (e: Exception) {
            tvResult.text = "Erro"
            firstNumber = ""
            currentOperation = ""
            newNumber = true
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculatePercentage() {
        try {
            val number = tvResult.text.toString().toDouble()
            val result = number / 100
            tvResult.text = formatResult(result)
            newNumber = true
        } catch (e: Exception) {
            tvResult.text = "Erro"
        }
    }

    @SuppressLint("DefaultLocale")
    private fun formatResult(result: Double): String {
        return if (result % 1 == 0.0) {
            result.toInt().toString()
        } else {
            String.format("%.2f", result)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun clearCalculator() {
        tvResult.text = "0"
        firstNumber = ""
        currentOperation = ""
        newNumber = true
    }
}
