package com.example.android.booklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import static com.example.android.booklist.MainActivity.LOG_TAG;

public class SearchBooks extends AppCompatActivity {

    EditText editText;
    ImageButton button;
    String searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books);



        editText = findViewById(R.id.input);
        button = findViewById(R.id.button);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchBooks.this,MainActivity.class);
                String searchInput = editText.getText().toString();
                searchInput = searchInput.replaceAll("\\s+","");
                searchResult = ("https://www.googleapis.com/books/v1/volumes?q="+ searchInput +"&maxResults=15");
                intent.putExtra("Data",searchResult);
                startActivity(intent);
            }
        });

    }
}
