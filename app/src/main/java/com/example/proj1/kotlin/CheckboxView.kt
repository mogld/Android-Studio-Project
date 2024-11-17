package com.example.proj1.kotlin

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import com.example.proj1.R

class CheckboxView : LinearLayout {
    private var checkBox: CheckBox? = null
    private var textView: TextView? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeViews(context)
    }

    constructor(context: Context) : super(context) {
        initializeViews(context)
    }

    private fun initializeViews(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.checkbox_view, this)
        checkBox = findViewById<CheckBox>(R.id.check)
        textView = findViewById<TextView>(R.id.title)
    }

    fun setText(text: String?) {
        textView!!.text = text
    }

    fun setOnCheckedChangeListener(listener: OnClickListener?) {
        checkBox!!.setOnClickListener(listener)
    }

    val isChecked: Boolean
        get() = checkBox!!.isChecked
}