package com.daigler.adaptivefinance;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.daigler.adaptivefinance.data.AdaptiveFinanceDatabaseHelper;
import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;
import com.mobeta.android.dslv.SimpleFloatViewManager;

import java.util.ArrayList;
import java.util.Arrays;

public class OverviewCustomizationActivity extends AppCompatActivity {
    private AdaptiveFinanceDatabaseHelper db;

    DragSortListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization_overview);

        Toolbar toolbar = findViewById(R.id.toolbarCustomization);
        toolbar.setTitle("OVERVIEW CUSTOMIZATION");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = new AdaptiveFinanceDatabaseHelper(this);

        listView = findViewById(R.id.listview);
        listView.setCacheColorHint(Color.WHITE);

        SimpleFloatViewManager simpleFloatViewManager = new SimpleFloatViewManager(listView);
        simpleFloatViewManager.setBackgroundColor(Color.TRANSPARENT);
        listView.setFloatViewManager(simpleFloatViewManager);

        ArrayList<String> fragmentList = db.getOverviewFragmentNameList();

        adapter = new ArrayAdapter<String>(this,
                R.layout.drag_list_items, R.id.menu_item_textview, fragmentList);
        listView.setAdapter(adapter);
        listView.setDropListener(onDrop);
        listView.setRemoveListener(onRemove);

        DragSortController controller = new DragSortController(listView);
        controller.setDragHandleId(R.id.ic_menu_swap);
        controller.setRemoveEnabled(false);
        controller.setSortEnabled(true);
        controller.setDragInitMode(1);

        listView.setFloatViewManager(controller);
        listView.setOnTouchListener(controller);
        listView.setDragEnabled(true);
    }

    private DragSortListView.DropListener onDrop = new DragSortListView.DropListener()
    {
        @Override
        public void drop(int from, int to) {
            if (from != to) {
                String item = adapter.getItem(from);
                adapter.remove(item);
                adapter.insert(item, to);

                String[] newListOrder = {
                        adapter.getItem(0),
                        adapter.getItem(1),
                        adapter.getItem(2),
                        adapter.getItem(3),
                        adapter.getItem(4)
                };

                db.updateOverviewFragmentOrder(newListOrder);
            }
        }
    };

    private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
        @Override
        public void remove(int which) {
            adapter.remove(adapter.getItem(which));
        }
    };
}
