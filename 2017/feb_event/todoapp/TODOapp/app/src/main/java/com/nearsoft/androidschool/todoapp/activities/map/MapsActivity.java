package com.nearsoft.androidschool.todoapp.activities.map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.activities.detail.DetailActivity;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private ToDoContent toDoContent;
    private final float TWO_HUNDRED_MTS_ZOOM = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (getIntent().hasExtra(DetailActivity.EXTRA_TODO_KEY)) {
            toDoContent = (ToDoContent) getIntent().getExtras()
                    .getSerializable(DetailActivity.EXTRA_TODO_KEY);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng toDoLatLng = new LatLng(toDoContent.getLat(), toDoContent.getLng());
        map.addMarker(new MarkerOptions().position(toDoLatLng).title(toDoContent.getTitle()));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(toDoLatLng, TWO_HUNDRED_MTS_ZOOM));
    }

}
