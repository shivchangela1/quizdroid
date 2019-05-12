package edu.washington.shivc.quizdroid
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class answers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answers)

        val answer = findViewById<TextView>(R.id.myAns)
        val correctAnswer = findViewById<TextView>(R.id.actualAns)
        val score = findViewById<TextView>(R.id.score)
        val button = findViewById<Button>(R.id.nextOrFinalBtn)

        val totalQuestions = intent.getIntExtra("totalQuestions", 1)
        val questionNum = intent.getIntExtra("questionNum", 1)
        val myAnswer = intent.getIntExtra("myAnswer", 1)

        answer.text = myAnswer.toString()
        correctAnswer.text = 1.toString() //set to an actual answer later

        val questionsCorrectText = "You have $questionNum out of $totalQuestions correct!"
        score.text = questionsCorrectText

        if (totalQuestions == questionNum) {
            button.text = "Finish"
        }

        button.setOnClickListener {
            if (button.text == "Finish") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, questions::class.java)
                intent.putExtra("totalQuestions", totalQuestions)
                intent.putExtra("questionNum", questionNum + 1)
                startActivity(intent)
            }
        }
    }
}