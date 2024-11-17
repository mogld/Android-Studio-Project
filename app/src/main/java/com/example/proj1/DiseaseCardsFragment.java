package com.example.proj1;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proj1.kotlin.Disease;
import com.example.proj1.kotlin.DiseaseRepository;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

public class DiseaseCardsFragment extends Fragment {

    private Button buttonGetRecommendation, buttonPrevious, buttonNext;
    private ImageView imageViewDisease;
    private TextView textViewDiseaseName, textViewDiseaseDescription;
    private int count = 0;
    private Disease[] diseases;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disease_cards, container, false);

        buttonPrevious = view.findViewById(R.id.buttonPrevious);
        buttonNext = view.findViewById(R.id.buttonNext);
        imageViewDisease = view.findViewById(R.id.imageViewDisease);
        textViewDiseaseName = view.findViewById(R.id.textViewDiseaseName);
        textViewDiseaseDescription = view.findViewById(R.id.textViewDiseaseDescription);

        updateDiseaseCard();


        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = (count - 1 + diseases.length) % diseases.length;
                updateDiseaseCard();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = (count + 1) % diseases.length;
                updateDiseaseCard();
            }
        });

        return view;
    }

    private void updateDiseaseCard() {
        DiseaseRepository diseaseRepository = new DiseaseRepository(getContext());
        this.diseases = diseaseRepository.getAllDiseases().toArray(new Disease[0]);

        String image = diseases[count].getIconImage();
        String name = diseases[count].getName();
        String description = diseases[count].getDescription();

        //download image from url
        GlideToVectorYou.justLoadImage(getActivity(), Uri.parse(image), imageViewDisease);
        textViewDiseaseName.setText(name);
        textViewDiseaseDescription.setText(description);
    }
}
