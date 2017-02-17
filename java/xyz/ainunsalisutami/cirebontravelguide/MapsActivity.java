package xyz.ainunsalisutami.cirebontravelguide;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xyz.ainunsalisutami.cirebontravelguide.helper.JSONParser;
import xyz.ainunsalisutami.cirebontravelguide.helper.JSONPeta;

public class MapsActivity extends FragmentActivity {
    private static String url = "http://test.mountoya.id/coba/tugasakhir/cirebon/peta.php";

    // JSON Node names
    private static final String TAG_TABEL = "peta";
    private static final String TAG_NAMA = "nama";
    //private static final String TAG_SNIPPET = "snippet";
    private static final String TAG_LATITUDE = "latitude";
    private static final String TAG_LONGITUDE = "longitude";
    private GoogleMap mMap;
    JSONArray data = null;
    static boolean a = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        try {
            // Loading map
            initializeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       // SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
               // .findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);
        //mMap.setMyLocationEnabled(true);
    }
private void initializeMap(){
//kalau mapnya belum dibuat, buat dulu :)
    if (mMap == null) {
        //Try to obtain the map from the SupportMapFragment.
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();
        //mapFragment.getMapAsync(this);
        //Check if we were successful in obtaining the map.
        //Sesuatu jika map berhasil ditampilkan
        if (mMap != null) {
            Log.e("Sukses","sukses");
            //Menampilkan tombol my location pada peta
            mMap.setMyLocationEnabled(true);
           // mMap.animateCamera(CameraUpdateFactory.zoomIn());
            //Menampilkan marker
            new JSONParse().execute();
        }
    }
}

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    //JSON parser, mengambil data dari web service,
    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute(){

        }
        //menjalankan proses di background, tidak mengganggu proses lain
        @Override
        protected JSONObject doInBackground(String... args)
        {
            //Membuat JSON Parser instance
            JSONPeta jParser = new JSONPeta();

            //mengambil JSON String dari url
            JSONObject json = jParser.getJSONFromUrl(url);
            if(json==null)
            {
                a=false;
            }
            else a=true;
            return json;
        }
        protected void onPostExecute(JSONObject json) {
            if(a==true)
            {
                try{
                    Log.e("status",a+"");
                    //mengambil array toilets
                    data = json.getJSONArray(TAG_TABEL);
                    data = json.getJSONArray(TAG_TABEL);

                    for(int i=0; i<data.length();i++)
                    {
                        JSONObject a = data.getJSONObject(i);
                        //simpan di variable
                        String nama = a.getString(TAG_NAMA);
                       // String snippet = a.getString(TAG_SNIPPET);
                        String latitude = a.getString(TAG_LATITUDE);
                        String longitude = a.getString(TAG_LONGITUDE);
                        Log.e("nama",nama);
                        //Log.e("snippet",snippet);
                        //konversi data dari String ke double
                        //data dari web service bertipe string
                        Double lat=Double.parseDouble(latitude.toString());
                        Double longi=Double.parseDouble(longitude.toString());
                        //add marker ke peta
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(lat, longi))
                                .title(nama));
                                //.snippet(snippet));
                    }
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
            //Jika tidak ada koneksi atau server down, keluarkan message error
            else {Toast.makeText(getApplicationContext(), "error getting data", Toast.LENGTH_SHORT).show();
                Log.e("status",a+"");}
        }

    }
}