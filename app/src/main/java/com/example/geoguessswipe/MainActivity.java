package com.example.geoguessswipe;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private TextView headerText;
    private RecyclerView recyclerView;
    List<GeoObject> mGeoObjects;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGeoObjects = new ArrayList<>();

        headerText = findViewById(R.id.textInstructions);
        recyclerView = findViewById(R.id.recyclerView);


        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES.length; i++) {
            mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES[i],
                    GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i], GeoObject.PRE_DEFINED_IN_EUROPE[i]));
        }

        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        GeoObjectAdapter mAdapter = new GeoObjectAdapter(this, mGeoObjects);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(this);


        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                GeoObject mGeoObj = mGeoObjects.get(position);

                if (direction == ItemTouchHelper.RIGHT && mGeoObj.isEurope()) {
                    Toast.makeText(MainActivity.this, "Incorrect! This is located in Europe", Toast.LENGTH_LONG).show();
                } else if (direction == ItemTouchHelper.RIGHT && !mGeoObj.isEurope()) {
                    Toast.makeText(MainActivity.this, "Correct! This is not located in Europe", Toast.LENGTH_LONG).show();
                } else if (direction == ItemTouchHelper.LEFT && !mGeoObj.isEurope()) {
                    Toast.makeText(MainActivity.this, "Incorrect! This is not located in Europe", Toast.LENGTH_LONG).show();
                } else if (direction == ItemTouchHelper.LEFT && mGeoObj.isEurope()) {
                    Toast.makeText(MainActivity.this, "Correct! This is located in Europe", Toast.LENGTH_LONG).show();
                }
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            int mAdapterPosition = rv.getChildAdapterPosition(child);
            if (child != null && mGestureDetector.onTouchEvent(e)) {
                Toast.makeText(this, mGeoObjects.get(mAdapterPosition).getmGeoName(), Toast.LENGTH_SHORT).show();
            }
            return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }


}
