package com.android.bignerdranch.samodelkin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val CHARACTER_DATA_KEY = "CHARACTER_DATA_KEY"

private var Bundle.characterData
    get() = getSerializable(CHARACTER_DATA_KEY) as CharacterGenerator.CharacterData
    set(value) = putSerializable(CHARACTER_DATA_KEY, value)

class MainActivity : AppCompatActivity() {
    private var characterData = CharacterGenerator.generate()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.characterData = characterData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // CHALLENGE:Live Data
        savedInstanceState?.let { characterData = it.characterData }
            ?: updateFromWeb()

        generateButton.setOnClickListener {
            updateFromWeb()
        }

        displayChracterData()
    }

    private fun displayChracterData() {
        characterData.run {
            nameTextView.text = name
            raceTextView.text = race
            dexterityTextView.text = dex
            wisdomTextView.text = wis
            strengthTextView.text = str
        }
    }

    private fun updateFromWeb() {
        GlobalScope.launch(Dispatchers.Main) {
            characterData = fetchCharacterdata().await()
            // CHALLENGE: Minimum Strength
            characterData.str.toInt().let {
                when {
                    it < 10 -> updateFromWeb()
                    else -> displayChracterData()
                }
            }
        }
    }
}
