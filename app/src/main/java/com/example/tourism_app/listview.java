package com.example.tourism_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class listview extends AppCompatActivity {

    private ListView  lv;
    String clati=null;
    String clongi=null;

    String name,vicinity,username;
    double rating;
    public DatabaseReference databaseReference;


    ArrayList<HashMap<String,String>> friendlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        friendlist = new ArrayList<>();
        lv=findViewById(R.id.listview);


        SharedPreferences prfs = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        clati = prfs.getString("lati", "");
        clongi = prfs.getString("longi","");


        System.out.println(clati);
        System.out.println(clongi);
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            clati = extras.getString("lati");
//            clongi = extras.getString("longi");
//            Toast.makeText(getApplicationContext(),clati.toString(),Toast.LENGTH_LONG).show();
//        }



        GetData getData = new GetData();
        getData.execute();

    }


    public class GetData extends AsyncTask<String,String,String>{

        public String Json_url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+ clati +"," + clongi +"&radius=5000&type=Restaurant%20&sensor=true&key=AIzaSyDKVZ7swTX2ftVuhGhLDxfUZx6beL2mzs8" ;



        @Override
        protected String doInBackground(String... strings) {
            String current="";
            try{
                URL url;
                HttpURLConnection urlConnection = null;
                try{
                    url=new URL(Json_url);
                    urlConnection=(HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);
                    int data = isr.read();
                    while(data != -1){
                        current +=(char) data;
                        data=isr.read();

                    }
                    return current;



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                if(urlConnection !=null){
                    urlConnection.disconnect();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try{
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for(int i=0; i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    name= jsonObject1.getString("name");
                    vicinity=jsonObject1.getString("vicinity");







                    HashMap<String,String> friends = new HashMap<>();
                    friends.put("name",name);
                    friends.put("vicinity",vicinity);


                    friendlist.add(friends);



                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            ListAdapter adapter = new SimpleAdapter(
                    listview.this,friendlist,R.layout.row_layout,
                    new String[] {"name","vicinity"},
                    new int[]{R.id.textView6,R.id.textview7});
            lv.setAdapter(adapter);



            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getApplicationContext(), adapter.getItem(i).toString(), Toast.LENGTH_SHORT).show();
//                    storedate(adapter.getItem(i));


                }
            });


        }
    }

    private void storedate(Object item) {


        SharedPreferences prf = getSharedPreferences("My", Context.MODE_PRIVATE);
        username = prf.getString("username", "");

        storehotel storenearby = new storehotel(clati,clongi,username);
        System.out.println(item);


        FirebaseDatabase db = FirebaseDatabase.getInstance();

        databaseReference = db.getReference("hotel");
        databaseReference.push().setValue(item,storenearby);
        databaseReference.push().setValue(storenearby);


    }
}