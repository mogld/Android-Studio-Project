package com.example.proj1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.kotlin.CheckboxView;
import com.example.proj1.kotlin.HealthCondition;
import com.example.proj1.kotlin.HealthConditionRepository;
import com.google.android.material.button.MaterialButton;

import java.util.LinkedList;
import java.util.List;

public class RecommendationActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private MaterialButton buttonSubmit;

    private LinearLayout checkBoxLayout;

    public void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                View w = getCurrentFocus();
                int[] location = new int[2];
                w.getLocationOnScreen(location);
                float x = event.getRawX() + w.getLeft() - location[0];
                float y = event.getRawY() + w.getTop() - location[1];
                if (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom()) {
                    hideKeyboard(this);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        HealthConditionRepository healthConditionRepository = new HealthConditionRepository(
                getApplicationContext()
        );


        List<HealthCondition> healthConditions = healthConditionRepository.getAllHealthConditions();

        checkBoxLayout = findViewById(R.id.checkboxLayout);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        List<Integer> checkedHealthConditions = new LinkedList<>();

        healthConditions.stream().forEach(healthCondition -> {
            CheckboxView checkboxView = new CheckboxView(this);
            checkboxView.setText(healthCondition.getName());
            checkboxView.setOnCheckedChangeListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkboxView.isChecked()) {
                        checkedHealthConditions.add((Integer) healthCondition.getFilterValue());
                    } else {
                        checkedHealthConditions.remove((Integer) healthCondition.getFilterValue());
                    }
                }
            });

            checkBoxLayout.addView(checkboxView);
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.getText().toString().isEmpty() || editTextAge.getText().toString().isEmpty()) {
                    Toast.makeText(RecommendationActivity.this, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());

                int checkedHealthCondition = checkedHealthConditions.stream().reduce(0, (a, b) -> a | b);

                Intent intent = new Intent(RecommendationActivity.this, VaccinationRecommendationActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("checkedHealthCondition", checkedHealthCondition);
                startActivity(intent);
            }
        });
    }
}
