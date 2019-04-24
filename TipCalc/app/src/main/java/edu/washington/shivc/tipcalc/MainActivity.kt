package edu.washington.shivc.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.text.TextWatcher
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var amount: EditText
    lateinit var btn: Button
    lateinit var tipPercentage: Spinner



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tipPercentage = findViewById(R.id.spinner)
        btn = findViewById(R.id.tipButton)
        btn.isEnabled = false
        amount = findViewById(R.id.amountInput)
        btn.setOnClickListener {
            val fullAmount = amount.text.substring(1, amount.length()).toDouble()
            val percent = tipPercentage.selectedItem.toString().dropLast(1).toInt()
            val tip = "%.2f".format(fullAmount * (percent * 0.01))
            Toast.makeText(applicationContext, "$$tip", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        amount.addTextChangedListener(object : TextWatcher {
            val textBox: TextView = amount
            var oldText = ""
            fun after(p0: Editable?) {
                if (amount.length() >= 3) {
                    if (amount.text.contains('.')) {
                        if (amount.text.indexOf('.', 0, true) == amount.length() - 4) {
                            textBox.text = oldText
                            amount.setSelection(amount.length())

                        }
                    }
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                oldText = amount.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btn.isEnabled = amount.text.isNotBlank()
                if (amount.length() >= 1) {
                    if (amount.text.toString()[0] != '$') {
                        textBox.text = "$${amount.text}"
                        amount.setSelection(amount.length())
                    }
                }

                if (amount.length() >= 3) {
                    if (amount.text.toString()[2] == '$') {
                        val newText = textBox.text.toString().replace("$", "", true)
                        textBox.text = "$${newText}"
                    }
                }
            }
        })
    }



}
