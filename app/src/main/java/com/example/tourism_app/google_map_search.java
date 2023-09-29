package com.example.tourism_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class google_map_search extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap googleMap;
    SupportMapFragment supportMapFragment;
    SearchView searchView;
    double lati = 0;
    double longi = 0;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_search);

        btn = findViewById(R.id.btndetails);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(google_map_search.this, listview.class);
//                intent.putExtra("lati","");
//                intent.putExtra("longi","");
                startActivity(intent);



                SharedPreferences preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("lati", String.valueOf(lati));
                editor.putString("longi",String.valueOf(longi));

                editor.apply();
                editor.commit();
            }
        });

        searchView = (SearchView) findViewById(R.id.sv_location);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location = searchView.getQuery().toString();

                List<Address> addressList = null;
                if ((location != null) || (!location.equals(""))) {
                    Geocoder geocoder = new Geocoder(google_map_search.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    Address address = addressList.get(0);


                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    googleMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    lati = address.getLatitude();
                    longi = address.getLongitude();
                    nearbyhotels(lati, longi);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        supportMapFragment.getMapAsync(this);


    }

    private void nearbyhotels(double lati, double longi) {
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                "?location=" + lati + "," + longi +
                "&radius=1000" +
                "&types=" + "cafe" +
                "&sensor=true" +
                "&key=" + getResources().getString(R.string.map_key);


        new HotelPlcaeTask().execute(url);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap Map) {
        googleMap = Map;

    }

    private class HotelPlcaeTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                data = downloadurl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            new HotelParserTask().execute(s);
        }
    }

    private String downloadurl(String string) throws IOException {
        URL url = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        StringBuilder builder = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            builder.append(line);

        }

        String data = builder.toString();
        reader.close();
        return data;
    }

    private class HotelParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {
        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            JsonParser jsonParser = new JsonParser();

            List<HashMap<String, String>> mapList = null;
            JSONObject object = null;


            try {
                object = new JSONObject(strings[0]);

                mapList = jsonParser.parseResult(object);


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            googleMap.clear();
            for (int i = 0; i < hashMaps.size(); i++) {
                HashMap<String, String> hashMapList = hashMaps.get(i);

                double lat = Double.parseDouble(Objects.requireNonNull(hashMapList.get("lat")));
                double lng = Double.parseDouble(Objects.requireNonNull(hashMapList.get("lng")));
                String name = hashMapList.get("name");

                LatLng latLng = new LatLng(lat, lng);
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                options.title(name);
                googleMap.addMarker(options);
            }
        }
    }
}