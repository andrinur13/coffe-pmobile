package com.example.responsi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Kopi_maps extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng yogyakarta = new LatLng(-7.797068, 110.370529);
            googleMap.addMarker(new MarkerOptions().position(yogyakarta).title("Marker in Yogyakarta"));

            LatLng homwoktamsis = new LatLng(-7.814439168443873, 110.37698067534355);
            googleMap.addMarker(new MarkerOptions().position(homwoktamsis).title("Homwok Taman Siswo"));

            LatLng PeachyCoffe = new LatLng(-7.812560250914397, 110.37637329140756);
            googleMap.addMarker(new MarkerOptions().position(PeachyCoffe).title("Peachy Coffee & Work Space"));

            LatLng asCoffe = new LatLng(-7.818717989374191, 110.37143216497702);
            googleMap.addMarker(new MarkerOptions().position(asCoffe).title("As Coffe"));

            LatLng malabar = new LatLng(-7.823819923630926, 110.36696896935925);
            googleMap.addMarker(new MarkerOptions().position(malabar).title("Malabar"));

            LatLng pako = new LatLng(-7.814593880189951, 110.36237702722839);
            googleMap.addMarker(new MarkerOptions().position(pako).title("Pako Coffe"));


            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yogyakarta, 13));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kopi_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}