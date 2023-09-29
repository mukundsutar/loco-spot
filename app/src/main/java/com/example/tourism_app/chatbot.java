package com.example.tourism_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class chatbot extends AppCompatActivity {

    private RecyclerView chatsRV;
    private ImageButton sendMsgIB;
    private EditText userMsgEdt;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";

    // creating a variable for
    // our volley request queue.
    private RequestQueue mRequestQueue;

    // creating a variable for array list and adapter class.
    private ArrayList<MessageModal> messageModalArrayList;
    private MessageRVAdapter messageRVAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        // on below line we are initializing all our views.
        chatsRV = findViewById(R.id.idRVChats);
        sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);

        // below line is to initialize our request queue.
        mRequestQueue = Volley.newRequestQueue(chatbot.this);
        mRequestQueue.getCache().clear();

        // creating a new array list
        messageModalArrayList = new ArrayList<>();

        sendMsgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userMsgEdt.getText().toString().isEmpty()) {
                    // if the edit text is empty display a toast message.
                    Toast.makeText(chatbot.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // calling a method to send message
                // to our bot to get response.
                sendMessage(userMsgEdt.getText().toString());

                // below line we are setting text in our edit text as empty
                userMsgEdt.setText("");
            }
        });

        // on below line we are initialing our adapter class and passing our array list to it.
        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, this);

        // below line we are creating a variable for our linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(chatbot.this, RecyclerView.VERTICAL, false);

        // below line is to set layout
        // manager to our recycler view.
        chatsRV.setLayoutManager(linearLayoutManager);

        // below line we are setting
        // adapter to our recycler view.
        chatsRV.setAdapter(messageRVAdapter);

    }

    private void sendMessage(String userMsg) {

        messageModalArrayList.add(new MessageModal(userMsg, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();

        // url for our brain
        // make sure to add mshape for uid.
        // make sure to add your url.

        String url = "http://api.brainshop.ai/get?bid=170377&key=NM4vi2XlAH2LBf5k&uid=[uid]&msg=" + userMsg;

        RequestQueue queue = Volley.newRequestQueue(chatbot.this);




        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // in on response method we are extracting data
                            // from json response and adding this response to our array list.



                            String botResponse = response.getString("cnt");
                            messageModalArrayList.add(new MessageModal(botResponse, BOT_KEY));

                            // notifying our adapter as data changed.
                            messageRVAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();

                            // handling error response from bot.
                            messageModalArrayList.add(new MessageModal("No response", BOT_KEY));
                            messageRVAdapter.notifyDataSetChanged();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("error"+error.toString());
                // error handling.
                messageModalArrayList.add(new MessageModal("Sorry no response found", BOT_KEY));
                Toast.makeText(chatbot.this, error.toString(), Toast.LENGTH_SHORT).show();
            }


        }){

        };



        queue.add(jsonObjectRequest);
    }

}