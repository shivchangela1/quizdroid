package edu.washington.shivc.quizdroid

import android.util.Log

class QuizApp : android.app.Application() {

    //Makes this into a singleton
    companion object Repository {
        val otherRepository = OtherRepository()
        val subjectNames = arrayOf("Math", "Physics", "Marvel Superheroes")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("appMessage", "Initialized")
    }
}