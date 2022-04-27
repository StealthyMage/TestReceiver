package com.example.testreceiver;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    TextView movies;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies=findViewById(R.id.textView_id);
        //setContentView(R.layout.recycler_layout);
        //recyclerView = findViewById(R.id.recycler_layout_id);
        //layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);


        //Week7
        Uri uri = Uri.parse("content://fit2081.week8.movie.provider/MovieDetails");
        Cursor result= getContentResolver().query(uri,null,null,null);
        movies.setText("Movies in Database = "+result.getCount()+"");
    }
}