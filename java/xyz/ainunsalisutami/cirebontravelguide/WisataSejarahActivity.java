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

public class WisataSejarahActivity extends Activity {

    private Context context;
    private static final String TAG = WisataSejarahActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private String json;
    private JSONParser jsonParser = new JSONParser();
    private JSONArray tempatwisata = null;
    public ArrayList<Wisata> listMockData;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_sejarah);

        context = this;

        listMockData = new ArrayList<Wisata>();
        new LoadWisata().execute();

        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Wisata newsData = (Wisata) listView.getItemAtPosition(position);

                Intent i = new Intent(getApplicationContext(), DetailWisataActivity.class);

                i.putExtra("id", newsData.getId());
                i.putExtra("nama", newsData.getNama());
                i.putExtra("alamat_wisata", newsData.getAlamat());
                i.putExtra("jenis_wisata", newsData.getJenis_wisata());
                i.putExtra("deskripsi", newsData.getDeskripsi());
                i.putExtra("gambar_wisata", newsData.getUrl());
                i.putExtra("lat", newsData.getLat());
                i.putExtra("lng", newsData.getLng());

                startActivity(i);
            }
        });
    }

    class LoadWisata extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(WisataSejarahActivity.this);
            pDialog.setMessage("Processing...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            json = jsonParser.makeHttpRequest(AppConfig.URL_WISATA_SEJARAH, "POST",
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
                            newsData.setUrl(c.getString("gambar_wisata"));
                            newsData.setNama(c.getString("nama"));
                            newsData.setAlamat(c.getString("alamat_wisata"));
                            newsData.setDeskripsi(c.getString("deskripsi"));
                            newsData.setId(c.getString("id"));
                            newsData.setJenis_wisata(c.getString("jenis_wisata"));
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
