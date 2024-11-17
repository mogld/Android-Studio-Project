package com.example.proj1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.kotlin.Disease;
import com.example.proj1.kotlin.DiseaseRepository;
import com.example.proj1.kotlin.RecommendationDiseaseCardView;
import com.example.proj1.kotlin.RecommendationService;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class VaccinationRecommendationActivity extends AppCompatActivity {

    private MaterialToolbar textViewTitle;
    private LinearLayout linearLayoutRecommendations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_recommendation);

        textViewTitle = findViewById(R.id.toolbar);
        linearLayoutRecommendations = findViewById(R.id.linearLayoutRecommendations);

        DiseaseRepository diseaseRepository = new DiseaseRepository(getApplicationContext());
        RecommendationService recommendationService = new RecommendationService(diseaseRepository);

        // 전달된 데이터를 가져옴
        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", 0);

        // 제목 설정
        String title = name + " 님을 위한 추천 백신";
        textViewTitle.setTitle(title);

        int checkedHealthCondition = getIntent().getIntExtra("checkedHealthCondition", 0);

        List<Disease> recommendedDiseases = recommendationService.getRecommendation(checkedHealthCondition, age);

        // 추천 백신을 네모 상자에 추가
        for (Disease recommendation : recommendedDiseases) {
            linearLayoutRecommendations.addView(new RecommendationDiseaseCardView(this, recommendation));
        }

    }

    public void goToHome(android.view.View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
