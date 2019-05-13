package edu.washington.shivc.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class App : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        val trans = supportFragmentManager.beginTransaction()
        val fragment = Topics()
        val bundle = Bundle()
        val subject = intent.getStringExtra("subject")

        bundle.putString("subject", subject)

        fragment.arguments = bundle

        trans.replace(R.id.fragmentLayout, fragment)
        trans.addToBackStack(null)
        trans.commit()

    }
}