package com.example.songgallery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;


public class Categories extends Fragment implements View.OnClickListener {

    private Button nextCategory, previousCategory;
    private Button shirim, chagim, tfilot, kriat_tora;
    private HorizontalScrollView hsv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        nextCategory = view.findViewById(R.id.next_category);
        nextCategory.setOnClickListener(this);
        previousCategory = view.findViewById(R.id.previous_category);
        previousCategory.setOnClickListener(this);
        shirim = view.findViewById(R.id.shirim);
        shirim.setOnClickListener(this);
        chagim = view.findViewById(R.id.chagim);
        chagim.setOnClickListener(this);
        tfilot = view.findViewById(R.id.tfilot);
        tfilot.setOnClickListener(this);
        kriat_tora = view.findViewById(R.id.kriat_tora);
        kriat_tora.setOnClickListener(this);
        hsv = view.findViewById(R.id.h_scroll_view);

        return view;
    }



    @Override
    public void onClick(View view) {
        if (nextCategory == view)
        {
            hsv.smoothScrollTo(hsv.getScrollX() - 1000, 0);
            shirim.setVisibility(View.VISIBLE);
            tfilot.setVisibility(View.VISIBLE);

            chagim.setVisibility(View.INVISIBLE);
            kriat_tora.setVisibility(View.INVISIBLE);
        }
        if (previousCategory == view)
        {
            hsv.smoothScrollTo(hsv.getScrollX() + 1000, 0);
            shirim.setVisibility(View.INVISIBLE);
            tfilot.setVisibility(View.INVISIBLE);

            chagim.setVisibility(View.VISIBLE);
            kriat_tora.setVisibility(View.VISIBLE);
        }
    }
}