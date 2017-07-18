package com.example.titivoradac.connectserverapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {


    private TextView txtShow;
    private String mResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtShow = (TextView)findViewById(R.id.txtResult);

        new FeedAsyncTask().execute("555");
    }

    public void feedData() {

    }

    public class FeedAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // tvResult.setText("Feed Data from http://date.jsontest.com/");
            Toast.makeText(MainActivity.this, "Connecting..", Toast.LENGTH_LONG).show();
            Log.i("testOkHTTP", "onPreExecute");
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.i("testOkHTTP", "doInBackground: " + strings[0]);

            final String _url = "http://date.jsontest.com/";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(_url).build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                } else {
                    return "Not Success - code : " + response.code();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "Error - " + e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(MainActivity.this, "Complete : " + s, Toast.LENGTH_LONG).show();
            Log.i("testOkHTTP", "onPostExecute : " + s);

        }
    }

}

