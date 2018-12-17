package com.example.abdelrahim.note;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private Button btn;
    private EditText edTitle,edContent;
    private DatabaseReference fNotesDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button) findViewById(R.id.btn_create);
        edTitle=(EditText)findViewById(R.id.edittext_title);
        edContent=(EditText)findViewById(R.id.edittext_content);

        fNotesDatabase=FirebaseDatabase.getInstance().getReference().child("Note");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Title=edTitle.getText().toString().trim();
                String Content=edContent.getText().toString().trim();

              if(!TextUtils.isEmpty(Title) && !TextUtils.isEmpty(Content)){

                  CreateNote(Title,Content);
              }
              else {
                  Toast toast = Toast.makeText(getApplicationContext(),
                          "Fill Empty fields",
                          Toast.LENGTH_SHORT);

                  toast.show();
              }

            }

            private void CreateNote(String title, String content) {

                fNotesDatabase.push();

                Map notemap=new HashMap();
                notemap.put("title",title);
                notemap.put("content",content);
                notemap.put("timestamp",ServerValue.TIMESTAMP);


            }
        });
    }
}
