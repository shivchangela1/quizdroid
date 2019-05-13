package edu.washington.shivc.quizdroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class Topics : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container!!.removeAllViews()
        return inflater!!.inflate(R.layout.activity_topics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectName = getView()!!.findViewById<TextView>(R.id.subjectName)
        val numQuestions = getView()!!.findViewById<TextView>(R.id.numQuestions)
        val description = getView()!!.findViewById<TextView>(R.id.description)
        val button = getView()!!.findViewById<Button>(R.id.beginButton)

        val subject = arguments!!.getString("subject")
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
            val fragment = Questions()

            val bundle = Bundle()
            bundle.putString("subject", subject)
            bundle.putInt("questionNum", 1)
            bundle.putInt("totalQuestions", numOfQuestions)

            fragment.arguments = bundle

            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragmentLayout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}