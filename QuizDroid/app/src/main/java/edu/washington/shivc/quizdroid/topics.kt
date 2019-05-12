package edu.washington.shivc.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class topics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)

        val subjectName = findViewById<TextView>(R.id.subjectName)
        val numQuestions = findViewById<TextView>(R.id.numQuestions)
        val description = findViewById<TextView>(R.id.description)
        val button = findViewById<Button>(R.id.beginButton)

        val subject = intent.getStringExtra("subject")
        val numOfQuestions = 4
        val numQuestionsText = "This topic contains $numOfQuestions questions"

        subjectName.text = subject

        description.text = when (subject) {
            "Math" -> "includes the study of such topics as quantity, structure, space, and change"
            "Physics" -> "the branch of science concerned with the nature and properties of matter and energy"
            else -> "a fictional universe where the stories in most American comic book titles and other media published by Marvel Comics take place"
        }

        numQuestions.text = numQuestionsText

        button.setOnClickListener {
            val intent = Intent(this, questions::class.java)
            intent.putExtra("subject", subject)
            intent.putExtra("questionNum", 1)
            intent.putExtra("totalQuestions", numOfQuestions)
            startActivity(intent)
        }
    }
}