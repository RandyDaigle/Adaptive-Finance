package com.daigler.adaptivefinance;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;
import android.widget.Toast;

public class TransactionsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView accountTextView;
    private TextView periodTextView;
    private FrameLayout transactionContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        transactionContainer = findViewById(R.id.transactionContainer);

        getSupportFragmentManager().beginTransaction().add(R.id.transactionContainer,
                new NoTransactionsFragment()).commit();

        accountTextView = findViewById(R.id.accountSpinnerTitleTextView);
        periodTextView = findViewById(R.id.periodSpinnerTitleTextView);

        accountTextView.setTextColor(Color.GRAY);
        periodTextView.setTextColor(Color.GRAY);

        Spinner accountSpinner = findViewById(R.id.accountChoiceSpinner);
        Spinner periodSpinner = findViewById(R.id.periodChoiceSpinner);

        accountSpinner.getBackground().setColorFilter(getColor(R.color.dot_active), PorterDuff.Mode.SRC_ATOP);
        periodSpinner.getBackground().setColorFilter(getColor(R.color.dot_active), PorterDuff.Mode.SRC_ATOP);

        String[] accountTypes = new String[] {
                "All CAD Accounts"
        };

        String[] periodTypes = new String[] {
                "This Month - March",
                "Last Month - February",
                "Next Month - April",
                "Last 7 Days",
                "Last 30 Days",
                "Last 60 Days"
        };

        ArrayAdapter<String> accountAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, accountTypes);
        accountSpinner.setAdapter(accountAdapter);

        ArrayAdapter<String> periodAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, periodTypes);
        periodSpinner.setAdapter(periodAdapter);

        /*accountSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/

        FloatingActionButton fab = findViewById(R.id.addTransactionFab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.expense_fab_background)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Under Construction.", Toast.LENGTH_LONG).show();
                /*Intent intent = new Intent(TransactionsActivity.this, NewTransactionActivity.class);
                startActivity(intent);
                finish();*/
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_transactions));
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_transactions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Toast.makeText(this, "Test", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_overview) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_accounts) {
            Intent intent = new Intent(this, AccountsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_transactions) {

        } else if (id == R.id.nav_reports) {
            Intent intent = new Intent(this, ReportsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
