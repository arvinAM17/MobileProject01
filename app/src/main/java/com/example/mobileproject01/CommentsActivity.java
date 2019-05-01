package com.example.mobileproject01;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class CommentsActivity extends AppCompatActivity {

    ArrayList<Comment> comments;
    Intent intent;
    int id;
    LinearLayout commentsLayout;
    TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        this.intent = getIntent();
        this.comments = (ArrayList<Comment>) intent.getSerializableExtra("Comments");
        this.id = intent.getIntExtra("Id", 0);
        this.commentsLayout = (LinearLayout) findViewById(R.id.comments);
        this.name = (TextView) findViewById(R.id.post_name);
        this.name.setText("Post " + Integer.toString(this.id) + ", " + Integer.toString(this.comments.size()) + " comments.");
        for (int i = 0; i < this.comments.size(); i++) {
            LinearLayout com = new LinearLayout(getApplicationContext());
            Comment current = this.comments.get(i);
            TextView postName = new TextView(getApplicationContext());
            postName.setText(current.name);
            postName.setTextColor(Color.GREEN);
            com.addView(postName);
            TextView body = new TextView(getApplicationContext());
            body.setText(current.body);
            com.addView(body);
            com.setOrientation(LinearLayout.VERTICAL);
            this.commentsLayout.addView(com);
        }

        configureBackButton();
    }

    private void configureBackButton() {
        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
