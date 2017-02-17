package xyz.ainunsalisutami.cirebontravelguide;
import android.app.Activity;
import android.content.Intent;
import android.renderscript.Double2;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class MapHotel extends AppCompatActivity  {

    //static final LatLng TutorialsPoint = new LatLng(21 , 57);
    private GoogleMap googleMap;
    //static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    //static final LatLng KIEL = new LatLng(53.551, 9.993);
    // latitude and longitude
    double latitude = 0;
    double longitude = 0;
    String title = "", nama, alamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_hotel);

        Intent i = getIntent();
        nama = i.getStringExtra("nama");
        alamat = i.getStringExtra("alamat");
        latitude = Double.valueOf(i.getStringExtra("lat"));
        longitude = Double.valueOf(i.getStringExtra("lng"));

        Toast.makeText(MapHotel.this, String.valueOf(latitude)+" "+String.valueOf(longitude), Toast.LENGTH_SHORT).show();

        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);



            //CARA PERTAMA
            //Marker TP = googleMap.addMarker(new MarkerOptions().position(HAMBURG).title("TutorialsPoint"));

            //CARA KEDUA
           /*Marker kiel = googleMap.addMarker(new MarkerOptions()
                   .position(KIEL)
                   .title("Kiel")
                   .snippet("Kiel is cool")
                   .icon(BitmapDescriptorFactory
                           .fromResource(R.mipmap.ic_launcher)));*/

            Marker kiel = googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .title(nama)
                    .snippet(alamat)
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.marker_hotel)));

            //Move the camera instantly to hamburg with a zoom of 15.
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
            // Zoom in, animating the camera.
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }




}