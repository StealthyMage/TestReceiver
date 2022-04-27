package com.example.testreceiver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    TextView movies;
    Button mButton;
    Button mGetButton;
    Button mDeleteButton;
    ContentResolver contentResolver;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies=findViewById(R.id.textView_id);
        mButton=findViewById(R.id.button);
        mGetButton = findViewById(R.id.getMovieButton);
        mDeleteButton = findViewById(R.id.deleteMovieButton);



        contentResolver =getContentResolver();
        Uri uri = Uri.parse("content://fit2081.week8.movie.provider/MovieDetails");
        Cursor result= contentResolver.query(uri,null,null,null);
        movies.setText("Movies in Database = "+result.getCount()+"");

        mButton.setOnClickListener( /*This tells the program to look for the button being pressed*/
                new View.OnClickListener() { /*This tells the program to let the user view whatever is defined in this function*/
                    public void onClick(View view) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("MovieName","Bob 3");
                    contentValues.put("MovieYear", "1999");
                    contentValues.put("MovieCountry","South Africa");
                    contentValues.put("MovieCost","40");
                    contentValues.put("MovieGenre","Horror");
                    contentValues.put("MovieKeywords", "Kinda Scary");
                    contentResolver.insert(uri,contentValues);
                    }
                }
        );
        mGetButton.setOnClickListener( /*This tells the program to look for the button being pressed*/
                new View.OnClickListener() { /*This tells the program to let the user view whatever is defined in this function*/
                    public void onClick(View view) {
                        Cursor result= contentResolver.query(uri,null,null,null);
                        movies.setText("Movies in Database = "+result.getCount()+"");
                    }
                }
        );
        mDeleteButton.setOnClickListener( /*This tells the program to look for the button being pressed*/
                new View.OnClickListener() { /*This tells the program to let the user view whatever is defined in this function*/
                    public void onClick(View view) {
                        contentResolver.delete(uri,"MovieCost" + "=?", new String[]{"> 100"});
                    }
                }
        );

    }
}