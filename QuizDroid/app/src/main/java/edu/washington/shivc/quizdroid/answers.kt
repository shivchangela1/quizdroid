package edu.washington.shivc.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class Answers : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container!!.removeAllViews()
        return inflater.inflate(R.layout.activity_answers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myAns = getView()!!.findViewById<TextView>(R.id.myAns)
        val actualAns = getView()!!.findViewById<TextView>(R.id.actualAns)
        val scoreBox = getView()!!.findViewById<TextView>(R.id.score)
        val btn = getView()!!.findViewById<Button>(R.id.nextOrFinalBtn)

        var score = arguments!!.getInt("score")
        val subject = arguments!!.getInt("subject")
        val totalQuestions = arguments!!.getInt("totalQuestions")
        val questionNum = arguments!!.getInt("questionNum")
        val myAnswer = arguments!!.getInt("myAnswer")

        myAns.text = myAnswer.toString()
        actualAns.text = QuizApp.otherRepository.getTopic(subject).questions[questionNum].answer.toString()

        if (myAnswer == QuizApp.otherRepository.getTopic(subject).questions[questionNum].answer) {
            score += 1
        }

        val scoreText = "You have ${score} out of $totalQuestions correct!"
        scoreBox.text = scoreText

        if (totalQuestions == questionNum + 1) {
            btn.text = "Finish"
        }

        btn.setOnClickListener {
            if (btn.text == "Finish") {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            } else {
                val fragment = Questions()
                val bundle = Bundle()
                bundle.putInt("score", score)
                bundle.putInt("totalQuestions", totalQuestions)
                bundle.putInt("questionNum", questionNum + 1)
                fragment.arguments = bundle

                val transaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.fragmentLayout, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }
}