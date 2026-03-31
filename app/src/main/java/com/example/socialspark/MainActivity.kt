package com.example.socialspark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socialspark.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.suggestButton.setOnClickListener {
            showSparkSuggestion()
        }

        binding.resetButton.setOnClickListener {
            binding.timeInputEditText.text?.clear()
            binding.suggestionTitle.text = getString(R.string.default_suggestion_title)
            binding.suggestionText.text = getString(R.string.default_suggestion_body)
            binding.timeInputLayout.error = null
        }
    }

    private fun showSparkSuggestion() {
        val userInput = binding.timeInputEditText.text?.toString()?.trim().orEmpty()
        val normalizedInput = userInput.lowercase(Locale.getDefault())

        if (normalizedInput.isEmpty()) {
            binding.timeInputLayout.error = getString(R.string.empty_input_error)
            binding.suggestionTitle.text = getString(R.string.default_suggestion_title)
            binding.suggestionText.text = getString(R.string.default_suggestion_body)
            return
        }

        binding.timeInputLayout.error = null

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
            binding.suggestionTitle.text = getString(R.string.unknown_time_title)
            binding.suggestionText.text = getString(R.string.unknown_time_message)
        }
    }
}
