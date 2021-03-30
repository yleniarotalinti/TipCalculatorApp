package com.example.tipcalculatorapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tipcalculatorapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding   //lateinit: a promise that I will inizialize that variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //BindingVariable Initialization
        setContentView(binding.root)
        binding.button1.setOnClickListener {
            calculateTip()
        }
        //to hide the soft keyboard when the user presses Enter
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
    }

    //prova commit
    private fun calculateTip() {
        val tip = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        if(tip==null){
            binding.resultView.text = "Insert a valid cost !"
            return
        }
        val buttonSelectedId = binding.RadioGroup.checkedRadioButtonId
        val percentage = when (buttonSelectedId) {
            R.id.Amazing_id -> 0.2
            R.id.Good_id -> 0.18
            else -> 0.15
        }
        val result = tip * percentage
        val round = binding.SwitchId.isChecked
        if (round) {
            kotlin.math.ceil(result)
        }
        val resultFormatted = NumberFormat.getInstance().format(result)
        binding.resultView.text = getString(R.string.tipResult, resultFormatted)
    }

    //handles the soft keyboard
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}