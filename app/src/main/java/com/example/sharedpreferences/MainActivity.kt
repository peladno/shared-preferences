package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.saveIntegerButton.setOnClickListener {
            val integerValue = binding.integerEditText.text.toString().toIntOrNull()
            if (integerValue != null) {
                sharedPrefs.edit().putInt("integerValue", integerValue).apply()
                displayMessage("Número entero guardado: $integerValue")
            } else {
                displayMessage("Ingrese un número entero válido")
            }
        }

        binding.saveTextButton.setOnClickListener {
            val textValue = binding.textEditText.text.toString()
            if (textValue.isNotBlank()) {
                sharedPrefs.edit().putString("textValue", textValue).apply()
                displayMessage("Texto guardado: $textValue")
            } else {
                displayMessage("Ingrese un texto válido")
            }
        }

        binding.saveBooleanButton.setOnClickListener {
            val booleanValue = binding.booleanRadioButton.isChecked
            sharedPrefs.edit().putBoolean("booleanValue", booleanValue).apply()
            displayMessage("Opción booleana guardada: $booleanValue")
        }

        binding.saveDecimalButton.setOnClickListener {
            val decimalValue = binding.decimalEditText.text.toString().toDoubleOrNull()
            if (decimalValue != null) {
                sharedPrefs.edit().putFloat("decimalValue", decimalValue.toFloat()).apply()
                displayMessage("Número decimal guardado: $decimalValue")
            } else {
                displayMessage("Ingrese un número decimal válido")
            }
        }

        // Mostrar los datos guardados en las preferencias
        displayInteger()
        displayText()
        displayBoolean()
        displayDecimal()
    }

    private fun displayMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun displayInteger() {
        val integerValue = sharedPrefs.getInt("integerValue", 0)
        binding.displayIntegerTextView.text = "Número entero guardado: $integerValue"
    }

    private fun displayText() {
        val textValue = sharedPrefs.getString("textValue", "")
        binding.displayTextTextView.text = "Texto guardado: $textValue"
    }

    private fun displayBoolean() {
        val booleanValue = sharedPrefs.getBoolean("booleanValue", false)
        binding.displayBooleanTextView.text = "Opción booleana guardada: $booleanValue"
    }

    private fun displayDecimal() {
        val decimalValue = sharedPrefs.getFloat("decimalValue", 0f)
        binding.displayDecimalTextView.text = "Número decimal guardado: $decimalValue"
    }
}
