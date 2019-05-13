package edu.washington.shivc.quizdroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class Questions : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        container!!.removeAllViews() //gets rid of all the old stuff
        return inflater.inflate(R.layout.activity_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionView = getView()!!.findViewById<TextView>(R.id.question) //add in text for question later
        val radioGroup = getView()!!.findViewById<RadioGroup>(R.id.radioGroup)
        val submitBtn = getView()!!.findViewById<Button>(R.id.submitButton)

        val score = arguments!!.getInt("score")
        val totalQuestions = arguments!!.getInt("totalQuestions")
        val questionNum = arguments!!.getInt("questionNum")
        val subject = arguments!!.getInt("subject")

        var myAnswer = 1 //placeholder
        val answers = QuizApp.otherRepository.getTopic(subject).questions[questionNum].answers
        val radioButtons = radioGroup.touchables

        questionView.text = QuizApp.otherRepository.getTopic(subject).questions[questionNum].question

        //Set answers on radio buttons
        for (i in 0..3) {
            val radioButton = radioButtons[i] as RadioButton
            radioButton.text = answers[i]
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            myAnswer = getView()!!.findViewById<RadioButton>(checkedId).text.toString().toInt()
            submitBtn.visibility = (View.VISIBLE)
        }

        submitBtn.setOnClickListener {
            val fragment = Answers()

            val bundle = Bundle()
            bundle.putInt("score", score)
            bundle.putInt("subject", subject)
            bundle.putInt("totalQuestions", totalQuestions)
            bundle.putInt("questionNum", questionNum)
            bundle.putInt("myAnswer", myAnswer)

            fragment.arguments = bundle

            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragmentLayout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}