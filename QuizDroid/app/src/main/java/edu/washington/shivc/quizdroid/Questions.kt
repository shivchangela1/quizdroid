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
    private var questionNum = 1
    private var totalQuestions = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        container!!.removeAllViews()
        return inflater.inflate(R.layout.activity_questions, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val radioGroup = getView()!!.findViewById<RadioGroup>(R.id.radioGroup)
        val submitBtn = getView()!!.findViewById<Button>(R.id.submitButton)

        totalQuestions = arguments!!.getInt("totalQuestions")
        questionNum = arguments!!.getInt("questionNum")
        var myAnswer = 1
        val answers = arrayOf("1", "2", "3", "4")
        val radioButtons = radioGroup.touchables

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