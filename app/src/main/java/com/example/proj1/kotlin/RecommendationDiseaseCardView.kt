package com.example.proj1.kotlin

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.proj1.R

class RecommendationDiseaseCardView : LinearLayout {
    constructor(context: Context, disease: Disease) : super(context) {
        initializeViews(context)
        this.setDiseaseName(disease.name)
    }

    private fun initializeViews(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.disease_card_view, this)
    }

    fun setDiseaseName(name: String) {
        val textView = findViewById<TextView>(R.id.disease_name)
        textView.text = name
    }
}