package com.example.sebas.this4that;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.graphics.BitmapFactory.decodeStream;

public class MainActivity extends AppCompatActivity {

    TextView name;
    TextView email;
    Uri photoURL;
    ProfilePictureView profilePictureView;

    String userDisplayName;
    String userEmail;

    private ImageView cardImage;
    private TextView cardText;

    private ListView listView;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private CustomRecyclerView adapter;

    private DatabaseReference databaseReference;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_listview_layout);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Timeline");
//
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){


           /* userDisplayName = user.getDisplayName();
            userEmail = user.getEmail();
            photoURL = user.getPhotoUrl();
            name.setText(userDisplayName);
            email.setText(userEmail);
*/
        }


    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public CardViewHolder(View itemView) {
            super(itemView);

            itemView = mView;
        }

        public void setTitle(String title){
            TextView cardTitle = (TextView) mView.findViewById(R.id.list_card_title);
            cardTitle.setText(title);
        }

        public void setDescription(String description){
            TextView cardDescription = (TextView) mView.findViewById(R.id.list_card_description);
            cardDescription.setText(description);
        }

    }


    @Override
    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Cards, CardViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Cards, CardViewHolder>(
                Cards.class, R.layout.card_row, CardViewHolder.class, databaseReference
        ) {
            @Override
            protected void populateViewHolder(CardViewHolder viewHolder, Cards model, int position) {

            }
        };

    }


    public void loginScreen(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



}
