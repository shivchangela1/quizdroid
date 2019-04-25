package edu.washington.shivc.lifecounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var points: () -> Array<TextView>
    lateinit var layouts: () -> Array<LinearLayout>
    lateinit var loser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        points = {
            var oneHP: TextView = findViewById(R.id.oneHP)
            var twoHP: TextView = findViewById(R.id.twoHP)
            var threeHP: TextView = findViewById(R.id.threeHP)
            var fourHP: TextView = findViewById(R.id.fourHP)
            arrayOf(oneHP, twoHP, threeHP, fourHP)
        }

        layouts = {
            val layoutOne: LinearLayout = findViewById(R.id.layoutOne)
            val layoutTwo: LinearLayout = findViewById(R.id.layoutTwo)
            val layoutThree: LinearLayout = findViewById(R.id.layoutThree)
            val layoutFour: LinearLayout = findViewById(R.id.layoutFour)
            arrayOf(layoutOne, layoutTwo, layoutThree, layoutFour)
        }

        loser = findViewById(R.id.loser)
    }

    override fun onStart() {
        super.onStart()
        var counter = 0
        for (layout in layouts()) {
            var btns = layout.touchables
            for (button in btns) {
                button.tag = counter
                button.setOnClickListener {
                    it as Button
                    var increase = 0
                    when (it.text.toString()) {
                        "+" -> increase = 1
                        "-" -> increase = -1
                        "+5" -> increase = 5
                        else -> increase = -5
                    }

                    var current = points()[button.tag as Int].text.substring(13)
                    val result = (current.toInt() + increase).toString()
                    points()[button.tag as Int].text = points()[button.tag as Int].text.substring(0, 13) + result

                    if (result.toInt() < 1) { // <= 0
                        loser.text = points()[button.tag as Int].text.substring(0, 9) + " is the loser"
                    }

                    var counter = 0
                    for (HP in points()) {
                        if (HP.text.substring(13).toInt() > 0) {
                            counter += 1
                        }
                    }

                    if (counter == 4) {
                        loser.text = ""
                    }
                }
            }
            counter += 1
        }
    }
}