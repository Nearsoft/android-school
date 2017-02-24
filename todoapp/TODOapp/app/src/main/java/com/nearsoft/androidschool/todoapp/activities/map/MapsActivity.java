package com.nearsoft.androidschool.todoapp.activities.map;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String CONTENT_EXTRA = "TODO_CONTENT";
    private GoogleMap map;
    private ToDoContent toDoContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(getIntent().hasExtra(CONTENT_EXTRA)){
            toDoContent = (ToDoContent) getIntent().getExtras().getSerializable(CONTENT_EXTRA);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

//        TODO get toDoLatLng from toDoContent variable
        LatLng toDoLatLng = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(toDoLatLng).title(toDoContent.getTitle()));
        map.moveCamera(CameraUpdateFactory.newLatLng(toDoLatLng));
    }

}
