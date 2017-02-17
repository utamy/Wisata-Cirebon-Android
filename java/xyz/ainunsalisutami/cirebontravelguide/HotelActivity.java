package xyz.ainunsalisutami.cirebontravelguide;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xyz.ainunsalisutami.cirebontravelguide.adapter.HotelListAdapter;
import xyz.ainunsalisutami.cirebontravelguide.app.AppConfig;
import xyz.ainunsalisutami.cirebontravelguide.helper.JSONParser;
import xyz.ainunsalisutami.cirebontravelguide.model.Hotel;

public class HotelActivity extends Activity {

    private Context context;
    private static final String TAG = HotelActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private String json;
    private JSONParser jsonParser = new JSONParser();
    private JSONArray hotelhotel = null;
    public ArrayList<Hotel> listMockData;
    private ListView listView;
    public static final String TAG_GAMBAR = "url";
    public static final String TAG_ID = "id";
    public static final String TAG_KET = "fasilitas";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_JENIS = "jenis_hotel";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String TAG_TLP = "no_tlp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        context = this;

        listMockData = new ArrayList<Hotel>();
        new LoadHotel().execute();

        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Hotel newsData = (Hotel) listView.getItemAtPosition(position);

            }
        });
    }

    class LoadHotel extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(HotelActivity.this);
            pDialog.setMessage("Processing...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            json = jsonParser.makeHttpRequest(AppConfig.URL_HOTEL, "POST",
                    params);
            Log.d("Track List JSON: ", json);
            try {
                JSONObject jObj = new JSONObject(json);

                if (jObj != null) {
                    hotelhotel = jObj.getJSONArray("hotel");
                    if (hotelhotel.length() > 0) {
                        json = "";
                        for (int i = 0; i < hotelhotel.length(); i++) {
                            JSONObject c = hotelhotel.getJSONObject(i);

                            Hotel newsData = new Hotel();
                            newsData.setId(c.getString(HotelActivity.TAG_ID));
                            newsData.setUrl(c.getString(HotelActivity.TAG_GAMBAR));
                            newsData.setNama(c.getString(HotelActivity.TAG_NAMA));
                            newsData.setAlamat(c.getString(HotelActivity.TAG_ALAMAT));
                            newsData.setJenis_hotel(c.getString(HotelActivity.TAG_JENIS));
                            newsData.setFasilitas(c.getString(HotelActivity.TAG_KET));
                            newsData.setNo_tlp(c.getString(HotelActivity.TAG_TLP));
                            newsData.setLat(c.getString(HotelActivity.LAT));
                            newsData.setLng(c.getString(HotelActivity.LNG));
                            listMockData.add(newsData);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    listView.setAdapter(new HotelListAdapter(context, listMockData));
                }
            });
        }
    }

    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
