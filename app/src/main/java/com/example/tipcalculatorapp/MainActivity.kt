package com.example.tipcalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

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
}