package com.daigler.adaptivefinance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.daigler.adaptivefinance.data.AdaptiveFinanceDatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AdaptiveFinanceDatabaseHelper db;

    private FrameLayout container1;
    private FrameLayout container2;
    private FrameLayout container3;
    private FrameLayout container4;
    private FrameLayout container5;

    private Boolean showOtherFABs;

    private ArrayList<String> fragmentOrderList;

    private com.github.clans.fab.FloatingActionButton fab;
    private com.github.clans.fab.FloatingActionButton addIncomeFab;
    private com.github.clans.fab.FloatingActionButton addExpenseFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.BLACK);

        container1 = findViewById(R.id.container_1);
        container2 = findViewById(R.id.container_2);
        container3 = findViewById(R.id.container_3);
        container4 = findViewById(R.id.container_4);
        container5 = findViewById(R.id.container_5);

        setFragmentOrder();

        showOtherFABs = false;

        //fab = (FloatingActionButton) findViewById(R.id.fab);
        //addIncomeFab = findViewById(R.id.addIncomeFab);
        //addExpenseFab = findViewById(R.id.addExpenseFab);

        final com.github.clans.fab.FloatingActionMenu floatingActionMenu = findViewById(R.id.fabMenu);

        //rootContainer = findViewById(R.id.root_container);
        /*rootContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (floatingActionMenu.isOpened())
                    floatingActionMenu.setClosedOnTouchOutside(true);
                return true;
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        updateFragmentOrder();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.overview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_rearrange:
                Intent intent = new Intent(this, OverviewCustomizationActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_refresh:
                finish();
                startActivity(getIntent());
                return true;
            case R.id.action_compact_account_hide:
                container1.setVisibility(View.GONE);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_overview) {

        } else if (id == R.id.nav_accounts) {
            Intent intent = new Intent(this, AccountsActivity.class);
            startActivity(intent);
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

    private void setFragmentOrder() {
        db = new AdaptiveFinanceDatabaseHelper(this);

        fragmentOrderList = db.getOverviewFragmentOrder();

        for (int i = 0; i < fragmentOrderList.size(); ++i) {

            String[] splitString = fragmentOrderList.get(i).split(":");


            if (splitString[0].equals("Accounts Overview") && splitString[1].equals("1")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_1,
                        new OverviewAccountsCompactViewFragment()).addToBackStack("container1").commit();
            } else if (splitString[0].equals("Accounts Overview") && splitString[1].equals("2")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_2,
                        new OverviewAccountsCompactViewFragment()).addToBackStack("container2").commit();
            } else if (splitString[0].equals("Accounts Overview") && splitString[1].equals("3")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_3,
                        new OverviewAccountsCompactViewFragment()).addToBackStack("container3").commit();
            } else if (splitString[0].equals("Accounts Overview") && splitString[1].equals("4")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_4,
                        new OverviewAccountsCompactViewFragment()).addToBackStack("container4").commit();
            } else if (splitString[0].equals("Accounts Overview") && splitString[1].equals("5")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_5,
                        new OverviewAccountsCompactViewFragment()).addToBackStack("container5").commit();
            }

            if (splitString[0].equals("Latest Transactions") && splitString[1].equals("1")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_1,
                        new OverviewLatestTransactionsFragment()).addToBackStack("container1").commit();
            } else if (splitString[0].equals("Latest Transactions") && splitString[1].equals("2")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_2,
                        new OverviewLatestTransactionsFragment()).addToBackStack("container2").commit();
            } else if (splitString[0].equals("Latest Transactions") && splitString[1].equals("3")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_3,
                        new OverviewLatestTransactionsFragment()).addToBackStack("container3").commit();
            } else if (splitString[0].equals("Latest Transactions") && splitString[1].equals("4")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_4,
                        new OverviewLatestTransactionsFragment()).addToBackStack("container4").commit();
            } else if (splitString[0].equals("Latest Transactions") && splitString[1].equals("5")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_5,
                        new OverviewLatestTransactionsFragment()).addToBackStack("container5").commit();
            }

            if (splitString[0].equals("Expense By Category") && splitString[1].equals("1")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_1,
                        new OverviewExpenseByCategoryFragment()).addToBackStack("container1").commit();
            } else if (splitString[0].equals("Expense By Category") && splitString[1].equals("2")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_2,
                        new OverviewExpenseByCategoryFragment()).addToBackStack("container2").commit();
            } else if (splitString[0].equals("Expense By Category") && splitString[1].equals("3")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_3,
                        new OverviewExpenseByCategoryFragment()).addToBackStack("container3").commit();
            } else if (splitString[0].equals("Expense By Category") && splitString[1].equals("4")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_4,
                        new OverviewExpenseByCategoryFragment()).addToBackStack("container4").commit();
            } else if (splitString[0].equals("Expense By Category") && splitString[1].equals("5")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_5,
                        new OverviewExpenseByCategoryFragment()).addToBackStack("container5").commit();
            }

            if (splitString[0].equals("Income Vs Expense") && splitString[1].equals("1")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_1,
                        new OverviewIncomeVsExpenseFragment()).addToBackStack("container1").commit();
            } else if (splitString[0].equals("Income Vs Expense") && splitString[1].equals("2")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_2,
                        new OverviewIncomeVsExpenseFragment()).addToBackStack("container2").commit();
            } else if (splitString[0].equals("Income Vs Expense") && splitString[1].equals("3")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_3,
                        new OverviewIncomeVsExpenseFragment()).addToBackStack("container3").commit();
            } else if (splitString[0].equals("Income Vs Expense") && splitString[1].equals("4")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_4,
                        new OverviewIncomeVsExpenseFragment()).addToBackStack("container4").commit();
            } else if (splitString[0].equals("Income Vs Expense") && splitString[1].equals("5")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_5,
                        new OverviewIncomeVsExpenseFragment()).addToBackStack("container5").commit();
            }

            if (splitString[0].equals("High Spending Alerts") && splitString[1].equals("1")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_1,
                        new OverviewHighSpendingAlertsFragment()).addToBackStack("container1").commit();
            } else if (splitString[0].equals("High Spending Alerts") && splitString[1].equals("2")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_2,
                        new OverviewHighSpendingAlertsFragment()).addToBackStack("container2").commit();
            } else if (splitString[0].equals("High Spending Alerts") && splitString[1].equals("3")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_3,
                        new OverviewHighSpendingAlertsFragment()).addToBackStack("container3").commit();
            } else if (splitString[0].equals("High Spending Alerts") && splitString[1].equals("4")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_4,
                        new OverviewHighSpendingAlertsFragment()).addToBackStack("container4").commit();
            } else if (splitString[0].equals("High Spending Alerts") && splitString[1].equals("5")) {
                getSupportFragmentManager().beginTransaction().add(R.id.container_5,
                        new OverviewHighSpendingAlertsFragment()).addToBackStack("container5").commit();
            }
        }
    }

    private void updateFragmentOrder() {
        db = new AdaptiveFinanceDatabaseHelper(this);

        fragmentOrderList = db.getOverviewFragmentOrder();

        for (int i = 0; i < fragmentOrderList.size(); ++i) {

            String[] splitString = fragmentOrderList.get(i).split(":");

            if (splitString[0].equals("Accounts Overview") && splitString[1].equals("1")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_1,
                        new OverviewAccountsCompactViewFragment()).addToBackStack("container1").commit();
            } else if (splitString[0].equals("Accounts Overview") && splitString[1].equals("2")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_2,
                        new OverviewAccountsCompactViewFragment()).addToBackStack("container2").commit();
            } else if (splitString[0].equals("Accounts Overview") && splitString[1].equals("3")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_3,
                        new OverviewAccountsCompactViewFragment()).addToBackStack("container3").commit();
            } else if (splitString[0].equals("Accounts Overview") && splitString[1].equals("4")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_4,
                        new OverviewAccountsCompactViewFragment()).addToBackStack("container4").commit();
            } else if (splitString[0].equals("Accounts Overview") && splitString[1].equals("5")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_5,
                        new OverviewAccountsCompactViewFragment()).addToBackStack("container5").commit();
            }

            if (splitString[0].equals("Latest Transactions") && splitString[1].equals("1")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_1,
                        new OverviewLatestTransactionsFragment()).addToBackStack("container1").commit();
            } else if (splitString[0].equals("Latest Transactions") && splitString[1].equals("2")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_2,
                        new OverviewLatestTransactionsFragment()).addToBackStack("container2").commit();
            } else if (splitString[0].equals("Latest Transactions") && splitString[1].equals("3")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_3,
                        new OverviewLatestTransactionsFragment()).addToBackStack("container3").commit();
            } else if (splitString[0].equals("Latest Transactions") && splitString[1].equals("4")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_4,
                        new OverviewLatestTransactionsFragment()).addToBackStack("container4").commit();
            } else if (splitString[0].equals("Latest Transactions") && splitString[1].equals("5")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_5,
                        new OverviewLatestTransactionsFragment()).addToBackStack("container5").commit();
            }

            if (splitString[0].equals("Expense By Category") && splitString[1].equals("1")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_1,
                        new OverviewExpenseByCategoryFragment()).addToBackStack("container1").commit();
            } else if (splitString[0].equals("Expense By Category") && splitString[1].equals("2")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_2,
                        new OverviewExpenseByCategoryFragment()).addToBackStack("container2").commit();
            } else if (splitString[0].equals("Expense By Category") && splitString[1].equals("3")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_3,
                        new OverviewExpenseByCategoryFragment()).addToBackStack("container3").commit();
            } else if (splitString[0].equals("Expense By Category") && splitString[1].equals("4")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_4,
                        new OverviewExpenseByCategoryFragment()).addToBackStack("container4").commit();
            } else if (splitString[0].equals("Expense By Category") && splitString[1].equals("5")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_5,
                        new OverviewExpenseByCategoryFragment()).addToBackStack("container5").commit();
            }

            if (splitString[0].equals("Income Vs Expense") && splitString[1].equals("1")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_1,
                        new OverviewIncomeVsExpenseFragment()).addToBackStack("container1").commit();
            } else if (splitString[0].equals("Income Vs Expense") && splitString[1].equals("2")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_2,
                        new OverviewIncomeVsExpenseFragment()).addToBackStack("container2").commit();
            } else if (splitString[0].equals("Income Vs Expense") && splitString[1].equals("3")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_3,
                        new OverviewIncomeVsExpenseFragment()).addToBackStack("container3").commit();
            } else if (splitString[0].equals("Income Vs Expense") && splitString[1].equals("4")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_4,
                        new OverviewIncomeVsExpenseFragment()).addToBackStack("container4").commit();
            } else if (splitString[0].equals("Income Vs Expense") && splitString[1].equals("5")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_5,
                        new OverviewIncomeVsExpenseFragment()).addToBackStack("container5").commit();
            }

            if (splitString[0].equals("High Spending Alerts") && splitString[1].equals("1")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_1,
                        new OverviewHighSpendingAlertsFragment()).addToBackStack("container1").commit();
            } else if (splitString[0].equals("High Spending Alerts") && splitString[1].equals("2")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_2,
                        new OverviewHighSpendingAlertsFragment()).addToBackStack("container2").commit();
            } else if (splitString[0].equals("High Spending Alerts") && splitString[1].equals("3")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_3,
                        new OverviewHighSpendingAlertsFragment()).addToBackStack("container3").commit();
            } else if (splitString[0].equals("High Spending Alerts") && splitString[1].equals("4")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_4,
                        new OverviewHighSpendingAlertsFragment()).addToBackStack("container4").commit();
            } else if (splitString[0].equals("High Spending Alerts") && splitString[1].equals("5")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_5,
                        new OverviewHighSpendingAlertsFragment()).addToBackStack("container5").commit();
            }
        }
    }
}
