package com.example.mobileproject01;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.view.Menu;

public class MainActivity extends AppCompatActivity implements Observer {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private boolean IsGridView = false;
    private DividerItemDecoration dividerItem;


    NotificationCenter notificationCenter = new NotificationCenter();
    MessageController messageController = MessageController.getInstance(MainActivity.this);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        Button changeView = (Button) findViewById(R.id.change_view);


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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationCenter.unregister(this);

    }


}
