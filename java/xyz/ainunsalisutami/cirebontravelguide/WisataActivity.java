package xyz.ainunsalisutami.cirebontravelguide;

import xyz.ainunsalisutami.cirebontravelguide.adapter.WisataListAdapter;
import xyz.ainunsalisutami.cirebontravelguide.app.AppConfig;
import xyz.ainunsalisutami.cirebontravelguide.helper.JSONParser;
import xyz.ainunsalisutami.cirebontravelguide.model.Wisata;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class WisataActivity extends Activity {

    private Context context;
    private static final String TAG = WisataAlamActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private String json;
    private JSONParser jsonParser = new JSONParser();
    private JSONArray tempatwisata = null;
    public ArrayList<Wisata> listMockData;
    private ListView listView;
    public static final String TAG_GAMBAR = "gambar_wisata";
    public static final String TAG_ID = "id";
    public static final String TAG_KET = "deskripsi";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_ALAMAT = "alamat_wisata";
    public static final String TAG_JENIS = "jenis_wisata";
    public static final String LAT = "lat";
    public static final String LNG = "lng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata);

        context = this;

        listMockData = new ArrayList<Wisata>();
        new LoadWisata().execute();

        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Wisata newsData = (Wisata) listView.getItemAtPosition(position);
            }
        });
    }

    class LoadWisata extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(WisataActivity.this);
            pDialog.setMessage("Processing...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            json = jsonParser.makeHttpRequest(AppConfig.URL_WISATA, "POST",
                    params);
            Log.d("Track List JSON: ", json);
            try {
                JSONObject jObj = new JSONObject(json);

                if (jObj != null) {
                    tempatwisata = jObj.getJSONArray("wisata");
                    if (tempatwisata.length() > 0) {
                        json = "";
                        for (int i = 0; i < tempatwisata.length(); i++) {
                            JSONObject c = tempatwisata.getJSONObject(i);

                            Wisata newsData = new Wisata();
                            newsData.setUrl(c.getString(WisataActivity.TAG_GAMBAR));
                            newsData.setNama(c.getString(WisataActivity.TAG_NAMA));
                            newsData.setAlamat(c.getString(WisataActivity.TAG_ALAMAT));
                            newsData.setDeskripsi(c.getString(WisataActivity.TAG_KET));
                            newsData.setId(c.getString(WisataActivity.TAG_ID));
                            newsData.setJenis_wisata(c.getString(WisataActivity.TAG_JENIS));
                            newsData.setLat(c.getString(WisataActivity.LAT));
                            newsData.setLng(c.getString(WisataActivity.LNG));
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
                    listView.setAdapter(new WisataListAdapter(context, listMockData));
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
