package com.example.proj1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {

    private Button buttonGetRecommendation;
    private RecyclerView recyclerViewHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        buttonGetRecommendation = view.findViewById(R.id.buttonGetRecommendation);
       // recyclerViewHome = view.findViewById(R.id.recyclerViewHome);

        buttonGetRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecommendationActivity.class);
                startActivity(intent);
            }
        });

        // RecyclerView 설정
        /*recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));*/
        // recyclerViewHome.setAdapter(...);

        return view;
    }
}
