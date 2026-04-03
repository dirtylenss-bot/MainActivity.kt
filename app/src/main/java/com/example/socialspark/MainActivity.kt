package com.example.socialspark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socialspark.databinding.ActivityMainBinding
import java.util.Locale
//SOCIAL SUGGESTION APPLICATION WHICH GIVES USER SUGGESTIONS FOR BETTER COMMUNICATION AND SOCIALIZING BASED ON APPROPRIATE TIME OF DAY.

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//OUTPUT FEATURE FOR THAT DISPLAYS SUGGESTIONS WHEN BUTTON IS PRESSED
        binding.suggestButton.setOnClickListener {
            showSparkSuggestion()
        }
//RESET FEATURE TO UNDO USER INPUT TO DEFAULT SETTINGS
        binding.resetButton.setOnClickListener {
            binding.timeInputEditText.text?.clear()
            binding.suggestionTitle.text = getString(R.string.default_suggestion_title)
            binding.suggestionText.text = getString(R.string.default_suggestion_body)
            binding.timeInputLayout.error = null
        }
    }

    private fun showSparkSuggestion() {
         // SIMPLIFY INPUT FOR USER SO OUTPUT IS NOT AFFECTED BY SPACES OR LETTER CASE
        val userInput = binding.timeInputEditText.text?.toString()?.trim().orEmpty()
        val normalizedInput = userInput.lowercase(Locale.getDefault())
//STANBY FEATURE
        if (normalizedInput.isEmpty()) {
            binding.timeInputLayout.error = getString(R.string.empty_input_error)
            binding.suggestionTitle.text = getString(R.string.default_suggestion_title)
            binding.suggestionText.text = getString(R.string.default_suggestion_body)
            return
        }

        binding.timeInputLayout.error = null
//SOCIAL SPARK SET IN DIFFERENT PERIODS OF THE DAY THAT WILL BE IMPLEMENTED WITH IF STATMENTS
        if (normalizedInput == "morning") {
            binding.suggestionTitle.text = getString(R.string.morning_title)
            binding.suggestionText.text = getString(R.string.morning_sparks)
        } else if (normalizedInput == "afternoon") {
            binding.suggestionTitle.text = getString(R.string.afternoon_title)
            binding.suggestionText.text = getString(R.string.afternoon_sparks)
        } else if (normalizedInput == "evening") {
            binding.suggestionTitle.text = getString(R.string.evening_title)
            binding.suggestionText.text = getString(R.string.evening_sparks)
        } else {
            //CORRECTIONS FOR INPUT ERRORS
            binding.suggestionTitle.text = getString(R.string.unknown_time_title)
            binding.suggestionText.text = getString(R.string.unknown_time_message)
        }
    }
}
