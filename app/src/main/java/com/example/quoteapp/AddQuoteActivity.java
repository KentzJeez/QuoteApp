package com.example.quoteapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddQuoteActivity extends AppCompatActivity {

    private EditText quoteEditText;
    private EditText authorEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);
        quoteEditText=(EditText) findViewById(R.id.editTextAuthor);
        authorEditText=(EditText) findViewById(R.id.editTextQuote);
        addButton=(Button) findViewById(R.id.btnAdd);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote=quoteEditText.getText().toString();
                String author=authorEditText.getText().toString();


                if(quote.isEmpty())
                {
                    quoteEditText.setError("Cannot be Empty");
                    return;
                }
                if(author.isEmpty()){
                    authorEditText.setError("Cannot be Empty");
                    return;
                }
                addQuoteToDB(quote,author);
            }

            private void addQuoteToDB(String quote, String author) {
                HashMap<String,Object>quoteHashmap=new HashMap<>();
                quoteHashmap.put("quote",quote);
                quoteHashmap.put("author",author);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference quotesRef = database.getReference("message");

                String key=quotesRef.push().getKey();
                quoteHashmap.put("key",key);

                quotesRef.child(key).setValue(quoteHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AddQuoteActivity.this, "Added", Toast.LENGTH_SHORT).show();
                        quoteEditText.getText().clear();
                        authorEditText.getText().clear();
                    }
                });



            }
        });
    }
}