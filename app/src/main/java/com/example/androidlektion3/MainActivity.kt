package com.example.androidlektion3

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidlektion3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding
private val questions = arrayOf("Whats the dogbreed in the picture?",
    "What is the dogbreed showing on the picture?",
    "Whats the breed?")

    private val options = arrayOf(arrayOf("Amstaff", "Corgi", "Greyhound"),
        arrayOf("Grand danois", "Chihuahua", "Papillon"),
        arrayOf("Cockerpoo", "Cocker spaniel", "Dalmatin"))

    private val correctAnswers = arrayOf(1, 0, 2)

    private val images = arrayOf(
        R.drawable.corgi,
        R.drawable.grand_danois,
        R.drawable.dalmatin
    )

    private var currentQuestionIndex = 0
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        displayQuestion()

        binding.opt1Btn.setOnClickListener {
            checkAnswer(0)
        }
        binding.opt2Btn.setOnClickListener {
            checkAnswer(1)
        }
        binding.opt3Btn.setOnClickListener {
            checkAnswer(2)
        }
        binding.restartButton.setOnClickListener {
            restartQuiz()
        }
        binding.exitButton.setOnClickListener {
            exitQuiz()
        }
    }

    private fun correctButtonColors(buttonIndex : Int){
        when(buttonIndex){
            0 -> binding.opt1Btn.setBackgroundColor(Color.GREEN)
            1 -> binding.opt2Btn.setBackgroundColor(Color.GREEN)
            2 -> binding.opt3Btn.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.opt1Btn.setBackgroundColor(Color.BLACK)
            1 -> binding.opt2Btn.setBackgroundColor(Color.BLACK)
            2 -> binding.opt3Btn.setBackgroundColor(Color.BLACK)
        }
    }

    private fun resetButtonColors(){
        binding.opt1Btn.setBackgroundColor(Color.rgb(94,5, 5))
        binding.opt2Btn.setBackgroundColor(Color.rgb(94,5, 5))
        binding.opt3Btn.setBackgroundColor(Color.rgb(94,5, 5))
    }

    private fun showResult(){
        val resultMessage = if (score < 2) {
            "You scored $score out of ${questions.size}. Try again!🥹"
        } else {
            "Gr8 job! You scored $score out of ${questions.size} 🥳"
        }

        binding.resultTv.text = resultMessage
        binding.resultTv.visibility = View.VISIBLE
        binding.restartButton.isEnabled = true
    }

    private fun displayQuestion(){
        binding.questionText.text = questions[currentQuestionIndex]
        binding.opt1Btn.text = options[currentQuestionIndex][0]
        binding.opt2Btn.text = options[currentQuestionIndex][1]
        binding.opt3Btn.text = options[currentQuestionIndex][2]
        binding.imageView.setImageResource(images[currentQuestionIndex])
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex < questions.size - 1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()}, 1000)
        } else {
            showResult()
        }
    }
    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false

        binding.resultTv.visibility = View.GONE
    }
    private fun exitQuiz(){
        finish()
    }
}