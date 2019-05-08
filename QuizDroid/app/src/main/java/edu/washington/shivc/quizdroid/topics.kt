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
        val numOfQuestions = 4 //get from intent later
        val details = "Description placeholder that will be initialized later!"
        val numQuestionsText = "This topic contains $numOfQuestions questions"

        subjectName.text = subject
        description.text = details
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