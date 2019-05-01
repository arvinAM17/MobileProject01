package com.example.mobileproject01;


import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Observer {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button changeView;
    private boolean IsGridView = false;
    private DividerItemDecoration dividerItem;


    NotificationCenter notificationCenter = new NotificationCenter();
    MessageController messageController = MessageController.getInstance(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        changeView = (Button) findViewById(R.id.change_view);


        messageController.posts.addAll(messageController.connectionManager.loadPosts());


        mAdapter = new PostAdapter(getApplicationContext(), messageController.posts, messageController);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        dividerItem = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(dividerItem);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        changeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IsGridView = !IsGridView;
                if (IsGridView) {
                    recyclerView.setLayoutManager(gridLayoutManager);
                    dividerItem.setOrientation(gridLayoutManager.getOrientation());
                } else {
                    recyclerView.setLayoutManager(linearLayoutManager);
                    dividerItem.setOrientation(linearLayoutManager.getOrientation());
                }
            }
        });


        notificationCenter.register(MainActivity.this);

        notificationCenter.dataLoaded();

    }


    @Override
    public void update() {
        mAdapter.notifyDataSetChanged();
//        recyclerView.removeAllViews();
//        for (int i = 0; i < messageController.posts.size(); i++) {
//            RelativeLayout rel = new RelativeLayout(getApplicationContext());
//            TextView title = new TextView(getApplicationContext()); // Prepare textview object programmatically
//            title.setText(messageController.posts.get(i).getTitle());
//            title.setId(2 * i);
//            rel.addView(title);
//            TextView body = new TextView(getApplicationContext());
//            body.setText(messageController.posts.get(i).body);
//            body.setId(2 * i + 1);
//            rel.addView(body);
//            recyclerView.addView(rel); // Add to ViewGroup using this method
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationCenter.unregister(this);

    }


}
