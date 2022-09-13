package com.example.wordleappalexanderramirez

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var buttonGuess: Button
    lateinit var et4LGuess: EditText
    lateinit var inputGuess1: TextView
    lateinit var inputGuess2: TextView
    lateinit var inputGuess3: TextView
    lateinit var correctCheck1: TextView
    lateinit var correctCheck2: TextView
    lateinit var correctCheck3: TextView
    lateinit var gameOverTV: TextView
    lateinit var correctWord: TextView
    lateinit var wordToGuess: String

    var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        buttonGuess = findViewById(R.id.buttonGuess)
        et4LGuess = findViewById(R.id.et4LGuess)
        inputGuess1 = findViewById(R.id.inputGuess1)
        inputGuess2 = findViewById(R.id.inputGuess2)
        inputGuess3 = findViewById(R.id.inputGuess3)
        correctCheck1 = findViewById(R.id.correctCheck1)
        correctCheck2 = findViewById(R.id.correctCheck2)
        correctCheck3 = findViewById(R.id.correctCheck3)
        gameOverTV = findViewById(R.id.gameOverTV)
        correctWord = findViewById(R.id.correctWord)

        correctWord.setText("Correct Word was: " + wordToGuess)
        correctWord.isVisible = false
        gameOverTV.isVisible = false

        var guess1 = ""
        var guess2 = ""
        var guess3 = ""
        var rightAnswer = ""


        buttonGuess.setOnClickListener {
            if (count == 1) {
                guess1 = et4LGuess.text.toString().uppercase()
                inputGuess1.text = guess1
                rightAnswer = checkGuess(guess1, wordToGuess)
                correctCheck1.text = rightAnswer
                count++
                et4LGuess.getText().clear();
                closeKeyBoard(et4LGuess)
            }
            else if (count == 2) {
                guess2 = et4LGuess.text.toString().uppercase()
                inputGuess2.text = guess2
                rightAnswer = checkGuess(guess2, wordToGuess)
                correctCheck2.text = rightAnswer
                count++
                et4LGuess.getText().clear();
                closeKeyBoard(et4LGuess)
            }
            else if (count == 3) {
                guess3 = et4LGuess.text.toString().uppercase()
                inputGuess3.text = guess3
                rightAnswer = checkGuess(guess3, wordToGuess)
                correctCheck3.text = rightAnswer
                count++
                et4LGuess.getText().clear();
                closeKeyBoard(et4LGuess)
            }
            if (count >= 4) {
                buttonGuess.isEnabled = false
                gameOverTV.isVisible = true
                correctWord.isVisible = true
            }
        }
    }
    private fun checkGuess(guess: String, wordToGuess : String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
    private fun closeKeyBoard(view: View) {
        val imm:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}