package com.daigler.adaptivefinance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.preference.PreferenceManager;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;

import com.daigler.adaptivefinance.data.AdaptiveFinanceDatabaseHelper;

public class AccountsActivity extends AppCompatActivity
    implements  NavigationView.OnNavigationItemSelectedListener {

    private AdaptiveFinanceDatabaseHelper db;

    private int activeAccountsCount;
    private int hiddenAccountsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        db = new AdaptiveFinanceDatabaseHelper(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString("CurrentUser", "");

        activeAccountsCount = 0;
        hiddenAccountsCount = 0;

        activeAccountsCount = db.getNumberOfActiveAccounts(username);
        hiddenAccountsCount = db.getNumberOfHiddenAccounts(username);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        String[] accountTypes = new String[] {
                "Accounts (" + activeAccountsCount + ")",
                "Hidden Accounts (" + hiddenAccountsCount + ")"
        };

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, accountTypes);

        myAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    if (activeAccountsCount == 0) {
                        NoAccountFoundFragment noAccountFoundFragment = new NoAccountFoundFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, noAccountFoundFragment)
                                .commit();
                    }
                    else {
                        ActiveAccountListFragment activeAccountListFragment = new ActiveAccountListFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, activeAccountListFragment);
                    }

                } else if (position == 1) {
                    if (hiddenAccountsCount == 0) {
                        NoAccountFoundFragment noAccountFoundFragment = new NoAccountFoundFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, noAccountFoundFragment)
                                .commit();
                    }
                    else {
                        HiddenAccountListFragment hiddenAccountListFragment = new HiddenAccountListFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, hiddenAccountListFragment);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_accounts));
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addNewAccountFab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.income_fab_background)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountsActivity.this, NewAccountActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accounts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_overview) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_accounts) {

        } else if (id == R.id.nav_transactions) {
            Intent intent = new Intent(this, TransactionsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_reports) {
            Intent intent = new Intent(this, ReportsActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
