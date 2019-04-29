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
            var one: TextView = findViewById(R.id.oneHP)
            var two: TextView = findViewById(R.id.twoHP)
            var three: TextView = findViewById(R.id.threeHP)
            var four: TextView = findViewById(R.id.fourHP)
            arrayOf(one, two, three, four)
        }

        layouts = {
            val one: LinearLayout = findViewById(R.id.layoutOne)
            val two: LinearLayout = findViewById(R.id.layoutTwo)
            val three: LinearLayout = findViewById(R.id.layoutThree)
            val four: LinearLayout = findViewById(R.id.layoutFour)
            arrayOf(one, two, three, four)
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
