package com.example.andrew.slices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefManager = PrefManager(this)
        button_example_1.setOnClickListener {
            prefManager.saveString(KEY_EXAMPLE_1, edit_example_1.text.toString())
            updateSlice("example1")
        }
    }

    override fun onResume() {
        super.onResume()
        text_example_2.text = prefManager.getInt(KEY_EXAMPLE_2).toString()
    }
}
