package edu.washington.shivc.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.text.TextWatcher
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var total: EditText
    lateinit var btn: Button
    lateinit var tip: Spinner



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tip = findViewById(R.id.spinner)
        btn = findViewById(R.id.tipButton)
        btn.isEnabled = false
        total = findViewById(R.id.amountInput)
        btn.setOnClickListener {
            val fullAmount = total.text.substring(1, total.length()).toDouble()
            val percent = tip.selectedItem.toString().dropLast(1).toInt()
            val tip = "%.2f".format(fullAmount * (percent * 0.01))
            Toast.makeText(applicationContext, "$$tip", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        total.addTextChangedListener(object : TextWatcher {
            val textBox: TextView = total
            var oldText = ""
            override fun afterTextChanged(p0: Editable?) {
                if (total.length() >= 3) {
                    if (total.text.contains('.')) {
                        if (total.text.indexOf('.', 0, true) == total.length() - 4) {
                            textBox.text = oldText
                            total.setSelection(total.length())

                        }
                    }
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                oldText = total.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btn.isEnabled = total.text.isNotBlank()
                if (total.length() >= 1) {
                    if (total.text.toString()[0] != '$') {
                        textBox.text = "$${total.text}"
                        total.setSelection(total.length())
                    }
                }

                if (total.length() >= 3) {
                    if (total.text.toString()[2] == '$') {
                        val newText = textBox.text.toString().replace("$", "", true)
                        textBox.text = "$${newText}"
                    }
                }
            }
        })
    }
}
