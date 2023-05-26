package com.tfg.mapas;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tfg.mapas.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationClient;
    private Location lastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Enable My Location layer if permission granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    lastKnownLocation = location;
                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10));
                }
            });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

        // Fetch markers from Firebase
        FirebaseDatabase.getInstance().getReference("CONTENEDORES").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot typeSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot districtSnapshot : typeSnapshot.getChildren()) {
                        for (DataSnapshot containerSnapshot : districtSnapshot.getChildren()) {
                            Double lat = containerSnapshot.child("LATITUD").getValue(Double.class);
                            Double lng = containerSnapshot.child("LONGITUD").getValue(Double.class);
                            String title = containerSnapshot.child("DIRECCION").getValue(String.class);
                            if (lat != null && lng != null && title != null) {
                                LatLng markerPos = new LatLng(lat, lng);
                                mMap.addMarker(new MarkerOptions().position(markerPos).title(title));
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }
}

//package com.tfg.mapas;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.FragmentActivity;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
//
//    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
//
//    private GoogleMap mMap;
//    private FusedLocationProviderClient fusedLocationClient;
//    private Location lastKnownLocation;
//    private Spinner spinnerContainers;
//    private ArrayAdapter<CharSequence> containerAdapter;
//    private String selectedContainerType;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
////        spinnerContainers = findViewById(R.id.spinner_containers);
////        containerAdapter = ArrayAdapter.createFromResource(this, R.array.container_types, android.R.layout.simple_spinner_item);
////        containerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        spinnerContainers.setAdapter(containerAdapter);
////        spinnerContainers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                selectedContainerType = parent.getItemAtPosition(position).toString();
////                refreshMarkers();
////            }
////
////            @Override
////            public void onNothingSelected(AdapterView<?> parent) {
////                selectedContainerType = null;
////                refreshMarkers();
////            }
////        });
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            mMap.setMyLocationEnabled(true);
//            fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
//                if (location != null) {
//                    lastKnownLocation = location;
//                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10));
//                }
//            });
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    LOCATION_PERMISSION_REQUEST_CODE);
//        }
//
//        refreshMarkers();
//    }
//
//    private void refreshMarkers() {
//        mMap.clear();
//
//        FirebaseDatabase.getInstance().getReference("CONTENEDORES").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot typeSnapshot : dataSnapshot.getChildren()) {
//                    if (selectedContainerType == null || selectedContainerType.equals(typeSnapshot.getKey())) {
//                        for (DataSnapshot districtSnapshot : typeSnapshot.getChildren()) {
//                            for (DataSnapshot containerSnapshot : districtSnapshot.getChildren()) {
//                                Double lat = containerSnapshot.child("LATITUD").getValue(Double.class);
//                                Double lng = containerSnapshot.child("LONGITUD").getValue(Double.class);
//                                String title = containerSnapshot.child("DIRECCION").getValue(String.class);
//                                if (lat != null && lng != null && title != null) {
//                                    LatLng markerPos = new LatLng(lat, lng);
//                                    mMap.addMarker(new MarkerOptions().position(markerPos).title(title));
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle possible errors.
//            }
//        });
//    }
//}