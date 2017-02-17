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

public class HotelBintang2 extends Activity {

    private Context context;
    private static final String TAG = HotelBintang2.class.getSimpleName();
    private ProgressDialog pDialog;
    private String json;
    private JSONParser jsonParser = new JSONParser();
    private JSONArray hotelhotel = null;
    public ArrayList<Hotel> listMockData;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_bintang2);

        context = this;

        listMockData = new ArrayList<Hotel>();
        new LoadHotel().execute();

        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Hotel newsData = (Hotel) listView.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(), DetailHotelActivity.class);

                i.putExtra("id", newsData.getId());
                i.putExtra("url", newsData.getUrl());
                i.putExtra("nama", newsData.getNama());
                i.putExtra("jenis_hotel", newsData.getJenis_hotel());
                i.putExtra("alamat", newsData.getAlamat());
                i.putExtra("fasilitas", newsData.getFasilitas());
                i.putExtra("no_tlp", newsData.getNo_tlp());
                i.putExtra("lat", newsData.getLat());
                i.putExtra("lng", newsData.getLng());

                startActivity(i);
            }
        });
    }

    class LoadHotel extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(HotelBintang2.this);
            pDialog.setMessage("Processing...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            json = jsonParser.makeHttpRequest(AppConfig.URL_HOTEL_BINTANG2, "POST",
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
                            newsData.setId(c.getString("id"));
                            newsData.setUrl(c.getString("url"));
                            newsData.setNama(c.getString("nama"));
                            newsData.setAlamat(c.getString("alamat"));
                            newsData.setJenis_hotel(c.getString("jenis_hotel"));
                            newsData.setFasilitas(c.getString("fasilitas"));
                            newsData.setNo_tlp(c.getString("no_tlp"));
                            newsData.setLat(c.getString("lat"));
                            newsData.setLng(c.getString("lng"));
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
