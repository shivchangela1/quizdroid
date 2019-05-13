package edu.washington.shivc.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class App : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragment = Topics()
        val bundle = Bundle()

        val subject = intent.getIntExtra("subject", 1)
        bundle.putInt("subject", subject)
        fragment.arguments = bundle
        transaction.replace(R.id.fragmentLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}