package com.example.songgallery;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Album_List extends Fragment {


    private LinearLayout parent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_album_list, container, false);

        parent = v.findViewById(R.id.album_list_layout);
        createAlbumLayout();

        return v;
    }

    private void createAlbumLayout() {
        for (int i = 3; i <= 30; i+=3) {

            ///////////////////////////////
            //  layout for each row
            RelativeLayout rowLayout = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams rowParams = new RelativeLayout.LayoutParams(-1, -2);
            rowParams.setMargins(0, 40, 0, 0);
            rowLayout.setLayoutParams(rowParams);


            ///////////////////////////////////////
            //  layout for cube1 + inside things
            RelativeLayout cube1layout = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams cube1params = new RelativeLayout.LayoutParams(270, 270);
            cube1params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            cube1layout.setLayoutParams(cube1params);
            cube1layout.setBackground(getResources().getDrawable(R.drawable.album_artist_track_style));
            cube1layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Artist_List artist_list = new Artist_List();
                    getParentFragmentManager().beginTransaction().replace(R.id.TheList, artist_list).addToBackStack(null).commit();
                }
            });

            TextView cube1text = new TextView(getContext());
            cube1text.setText("אלבום");
            cube1text.setGravity(Gravity.CENTER);
            cube1text.setTextSize(20);
            cube1text.setTextColor(Color.BLACK);
            cube1text.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));

            CheckBox cube1checkbox = new CheckBox(getContext());
            cube1checkbox.setId(i-2);
            RelativeLayout.LayoutParams cube1checkboxparams = new RelativeLayout.LayoutParams(-2, -2);
            cube1checkboxparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            cube1checkbox.setLayoutParams(cube1checkboxparams);
            cube1checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Toast.makeText(getContext(), cube1checkbox.getId()+"", Toast.LENGTH_SHORT).show();
                }
            });

            cube1layout.addView(cube1text);
            cube1layout.addView(cube1checkbox);




            ///////////////////////////////////////
            //  layout for cube2 + inside things
            RelativeLayout cube2layout = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams cube2params = new RelativeLayout.LayoutParams(270, 270);
            cube2params.addRule(RelativeLayout.CENTER_IN_PARENT);
            cube2layout.setLayoutParams(cube2params);
            cube2layout.setBackground(getResources().getDrawable(R.drawable.album_artist_track_style));
            cube2layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Artist_List artist_list = new Artist_List();
                    getParentFragmentManager().beginTransaction().replace(R.id.TheList, artist_list).addToBackStack(null).commit();
                }
            });

            TextView cube2text = new TextView(getContext());
            cube2text.setText("אלבום");
            cube2text.setGravity(Gravity.CENTER);
            cube2text.setTextSize(20);
            cube2text.setTextColor(Color.BLACK);
            cube2text.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));

            CheckBox cube2checkbox = new CheckBox(getContext());
            cube2checkbox.setId(i-1);
            cube2checkbox.setLayoutParams(cube1checkboxparams);
            cube2checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Toast.makeText(getContext(), cube2checkbox.getId()+"", Toast.LENGTH_SHORT).show();
                }
            });

            cube2layout.addView(cube2text);
            cube2layout.addView(cube2checkbox);


            ///////////////////////////////////////
            //  layout for cube3 + inside things
            RelativeLayout cube3layout = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams cube3params = new RelativeLayout.LayoutParams(270, 270);
            cube3params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            cube3layout.setLayoutParams(cube3params);
            cube3layout.setBackground(getResources().getDrawable(R.drawable.album_artist_track_style));

            TextView cube3text = new TextView(getContext());
            cube3text.setText("אלבום");
            cube3text.setGravity(Gravity.CENTER);
            cube3text.setTextSize(20);
            cube3text.setTextColor(Color.BLACK);
            cube3text.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            cube3layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Artist_List artist_list = new Artist_List();
                    getParentFragmentManager().beginTransaction().replace(R.id.TheList, artist_list).addToBackStack(null).commit();
                }
            });

            CheckBox cube3checkbox = new CheckBox(getContext());
            cube3checkbox.setId(i);
            cube3checkbox.setLayoutParams(cube1checkboxparams);
            cube3checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Toast.makeText(getContext(), cube3checkbox.getId()+"", Toast.LENGTH_SHORT).show();
                }
            });

            cube3layout.addView(cube3text);
            cube3layout.addView(cube3checkbox);





            rowLayout.addView(cube1layout);
            rowLayout.addView(cube2layout);
            rowLayout.addView(cube3layout);
            parent.addView(rowLayout);
        }
    }


}