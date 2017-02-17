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

import xyz.ainunsalisutami.cirebontravelguide.adapter.KulinerListAdapter;
import xyz.ainunsalisutami.cirebontravelguide.app.AppConfig;
import xyz.ainunsalisutami.cirebontravelguide.helper.JSONParser;
import xyz.ainunsalisutami.cirebontravelguide.model.Kuliner;

public class KulinerMakananActivity extends Activity {
    private Context context;
    private static final String TAG = KulinerMakananActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private String json;
    private JSONParser jsonParser = new JSONParser();
    private JSONArray kulineran = null;
    public ArrayList<Kuliner> listMockData;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuliner_makanan);

        context = this;

        listMockData = new ArrayList<Kuliner>();
        new LoadKuliner().execute();

        listView = (ListView) findViewById(R.id.list);
        //listView.setAdapter(new WisataListAdapter(this, listData));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Kuliner newsData = (Kuliner) listView.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(), DetailKulinerActivity.class);

                i.putExtra("id_kuliner", newsData.getId_kuliner());
                i.putExtra("nama", newsData.getNama());
                i.putExtra("url", newsData.getUrl());
                i.putExtra("jenis_kuliner", newsData.getJenis_kuliner());
                i.putExtra("deskripsi", newsData.getDeskripsi());

                startActivity(i);
            }
        });
    }

    class LoadKuliner extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(KulinerMakananActivity.this);
            pDialog.setMessage("Processing...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            json = jsonParser.makeHttpRequest(AppConfig.URL_KULINER_MAKANAN, "POST",
                    params);
            Log.d("Track List JSON: ", json);
            try {
                JSONObject jObj = new JSONObject(json);

                if (jObj != null) {
                    kulineran = jObj.getJSONArray("kuliner");
                    if (kulineran.length() > 0) {
                        json = "";
                        for (int i = 0; i < kulineran.length(); i++) {
                            JSONObject c = kulineran.getJSONObject(i);

                            Kuliner newsData = new Kuliner();
                            newsData.setUrl(c.getString("url"));
                            newsData.setNama(c.getString("nama"));
                            newsData.setDeskripsi(c.getString("deskripsi"));
                            newsData.setId_kuliner(c.getString("id_kuliner"));
                            newsData.setJenis_kuliner(c.getString("jenis_kuliner"));

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
                    listView.setAdapter(new KulinerListAdapter(context, listMockData));
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
