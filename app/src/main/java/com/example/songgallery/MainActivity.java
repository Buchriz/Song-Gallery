package com.example.songgallery;

import static android.app.PendingIntent.getActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.internal.EdgeToEdgeUtils;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private Spinner spinner;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        spinner = findViewById(R.id.FilterSpinner);
        Album_List album_list = new Album_List();
        getSupportFragmentManager().beginTransaction().replace(R.id.TheList, album_list).addToBackStack(null).commit();


        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Filter_By, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedItem = spinner.getSelectedItem().toString();
        switch (selectedItem) {
            case "artist":
                Artist_List artist_list = new Artist_List();
                getSupportFragmentManager().beginTransaction().replace(R.id.TheList, artist_list).addToBackStack(null).commit();
                break;
            case "album":
                Album_List album_list = new Album_List();
                getSupportFragmentManager().beginTransaction().replace(R.id.TheList, album_list).addToBackStack(null).commit();
                break;
            case "track":
                Track_List track_list = new Track_List();
                getSupportFragmentManager().beginTransaction().replace(R.id.TheList, track_list).addToBackStack(null).commit();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}


}