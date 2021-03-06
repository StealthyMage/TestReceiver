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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView movies;
    Button mButton;
    Button mGetButton;
    Button mDeleteButton;
    ContentResolver contentResolver;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    Button mDelete2010Button;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies=findViewById(R.id.textView_id);
        mButton=findViewById(R.id.button);
        mGetButton = findViewById(R.id.getMovieButton);
        mDeleteButton = findViewById(R.id.deleteMovieButton);
        mDelete2010Button = findViewById(R.id.deleteMoviesOlderThan2010);

        contentResolver =getContentResolver();
        Uri uri = Uri.parse("content://fit2081.app.Connor/MovieDetails");
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
                    MovieDetails movieDetails = new MovieDetails("Bob 3", "1999","South Africa", "40", "Horror", "Kinda Scary");
                    ref.push().setValue(movieDetails);
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
                        String where = "MovieCost < 1000";
                        contentResolver.delete(uri,where, null);
                    }
                }
        );
        mDelete2010Button.setOnClickListener( /*This tells the program to look for the button being pressed*/
                new View.OnClickListener() { /*This tells the program to let the user view whatever is defined in this function*/
                    public void onClick(View view) {
                        String where = "MovieYear < 2010";
                        contentResolver.delete(uri,where, null);
                    }
                }
        );

    }
}