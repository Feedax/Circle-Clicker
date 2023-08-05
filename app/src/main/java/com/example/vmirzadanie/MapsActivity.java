package com.example.vmirzadanie;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.vmirzadanie.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    int MarkerCounter = 0;
    private ArrayList<Marker> mm = new ArrayList<>();
    private ArrayList<Marker> helpM = new ArrayList<>();
    private ArrayList<Polyline> po = new ArrayList<>();
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.calcId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        binding.clearId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MarkerCounter < 1) {
                    Toast.makeText(getApplicationContext(), "There is no marker to remove!", Toast.LENGTH_LONG).show();
                } else {
                    if(mm.size() > 1){
                        removeMarker();
                        calculate();
                    }else{
                        removeMarker();
                    }
                }
            }
        });

        binding.menuId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivity.this, MainMenu.class));
                finish();
            }
        });

    }

    private void removeMarker() {
        mm.get(mm.size() - 1).remove();
        mm.remove(mm.size() - 1);
        MarkerCounter = MarkerCounter - 1;
        if(po.size() > 0){
            po.get(po.size() - 1).remove();
            po.remove(po.size() - 1);
            if(x > 0){
                x = x-1;
            }
        }
    }


    private void calculate() {
        for(int i = 0; i < po.size(); i++) {
            po.get(i).remove();
        }

        int tmp = 0;

        if(mm.size() % 2 == 0){
            for(int i = mm.size(); i >= 0; i--){
                double x = 9999999;
                helpM.add(mm.get(tmp));
                String[] s1 = mm.get(tmp).getPosition().toString().replaceAll("[lat/lng: ()]", "").split(",");
                double d1 = Double.parseDouble(s1[0]);
                double d2 = Double.parseDouble(s1[1]);

                Location m1 = new Location("m1");
                m1.setLatitude(d1);
                m1.setLongitude(d2);

                for(int j = 0; j < mm.size(); j++){

                    if(!helpM.contains(mm.get(j))){
                        String[] s2 = mm.get(j).getPosition().toString().replaceAll("[lat/lng: ()]", "").split(",");
                        double d3 = Double.parseDouble(s2[0]);
                        double d4 = Double.parseDouble(s2[1]);

                        Location m2 = new Location("m2");
                        m2.setLatitude(d3);
                        m2.setLongitude(d4);

                        float distance = m1.distanceTo(m2) / 1000; // in km

                        if(distance < x){
                            x = distance;
                            tmp = j;
                        }
                    }
                }
            }
        }else{
            for(int i = 0; i < mm.size(); i++){
                double x = 9999999;
                helpM.add(mm.get(tmp));
                String[] s1 = mm.get(tmp).getPosition().toString().replaceAll("[lat/lng: ()]", "").split(",");
                double d1 = Double.parseDouble(s1[0]);
                double d2 = Double.parseDouble(s1[1]);

                Location m1 = new Location("m1");
                m1.setLatitude(d1);
                m1.setLongitude(d2);

                for(int j = mm.size() - 1; j >= 0; j--){

                    if(!helpM.contains(mm.get(j))){
                        String[] s2 = mm.get(j).getPosition().toString().replaceAll("[lat/lng: ()]", "").split(",");
                        double d3 = Double.parseDouble(s2[0]);
                        double d4 = Double.parseDouble(s2[1]);

                        Location m2 = new Location("m2");
                        m2.setLatitude(d3);
                        m2.setLongitude(d4);

                        float distance = m1.distanceTo(m2) / 1000; // in km

                        if(distance < x){
                            x = distance;
                            tmp = j;
                        }
                    }
                }
            }
        }


        for(int i = 0; i < helpM.size() - 1; i++){
            String[] s1 = helpM.get(i).getPosition().toString().replaceAll("[lat/lng: ()]", "").split(",");
            double d1 = Double.parseDouble(s1[0]);
            double d2 = Double.parseDouble(s1[1]);

            String[] s2 = helpM.get(i + 1).getPosition().toString().replaceAll("[lat/lng: ()]", "").split(",");
            double d3 = Double.parseDouble(s2[0]);
            double d4 = Double.parseDouble(s2[1]);

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(d1, d2), new LatLng(d3, d4))
                    .width(5)
                    .color(Color.RED));
            po.add(line);
        }
        helpM.clear();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                if (MarkerCounter < 10) {
                    MarkerCounter = MarkerCounter + 1;
                    Marker marker = mMap.addMarker(new MarkerOptions().position(latLng));
                    mm.add(marker);
                    System.out.println( "mm: " + mm);


                    if(mm.size() > 1){
                        x = x + 1;

                        LatLng m1 = mm.get(x-1).getPosition();
                        LatLng m2 = mm.get(x).getPosition();


                        Polyline line = mMap.addPolyline(new PolylineOptions()
                                .add(new LatLng(m1.latitude, m1.longitude),new LatLng(m2.latitude, m2.longitude))
                                .width(5)
                                .color(Color.RED));
                        po.add(line);
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Only 10 markers are allowed!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}