package com.daigler.adaptivefinance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.daigler.adaptivefinance.data.AdaptiveFinanceDatabaseHelper;
import com.nmaltais.calcdialog.CalcDialog;

import java.math.BigDecimal;

public class NewAccountActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, CalcDialog.CalcDialogCallback {

    private AdaptiveFinanceDatabaseHelper db;

    private EditText newAccountNameEditText;
    private ImageView newAccountIconImageView;
    private EditText newAccountStartingBalanceEditText;
    private TextView newAccountCurrencyTextView;
    private EditText newAccountDescriptionEditText;
    private Switch newAccountNotificationSwitch;
    private TextView newAccountMonthlyBudgetTextView;
    private Spinner newAccountDefaultTransactionStatusSpinner;
    private RadioGroup newAccountExcludeFromTotalBalanceRadioGroup;
    private RadioButton excludeFromTotalBalanceYes;
    private RadioButton excludeFromTotalBalanceNo;
    private RadioGroup newAccountHideRadioGroup;
    private RadioButton hideYes;
    private RadioButton hideNo;

    private Button cancelButton;
    private Button saveButton;

    private Boolean insertNewAccountResult;

    private BigDecimal value;
    final CalcDialog calcDialog = new CalcDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        Toolbar toolbar = findViewById(R.id.newAccountToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("NEW ACCOUNT");
        toolbar.setTitleTextColor(Color.BLACK);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.new_account_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavigationView navigationView = findViewById(R.id.new_account_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        newAccountNameEditText = findViewById(R.id.newAccountName);
        newAccountIconImageView = findViewById(R.id.newAccountIcon);
        newAccountStartingBalanceEditText = findViewById(R.id.newAccountStartingBalance);
        newAccountCurrencyTextView = findViewById(R.id.newAccountCurrency);
        newAccountDescriptionEditText = findViewById(R.id.newAccountDescription);
        newAccountNotificationSwitch = findViewById(R.id.newAccountNotificationSwitch);
        newAccountMonthlyBudgetTextView = findViewById(R.id.newAccountMonthlyBudget);
        newAccountDefaultTransactionStatusSpinner = findViewById(R.id.newAccountDefaultTransactionStatus);
        newAccountExcludeFromTotalBalanceRadioGroup = findViewById(R.id.newAccountExcludeFromTotalBalance);
        newAccountHideRadioGroup = findViewById(R.id.newAccountHide);

        excludeFromTotalBalanceYes = findViewById(R.id.excludeFromTotalBalanceYes);
        excludeFromTotalBalanceNo = findViewById(R.id.excludeFromTotalBalanceNo);

        excludeFromTotalBalanceYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excludeFromTotalBalanceNo.setChecked(false);
            }
        });

        excludeFromTotalBalanceNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excludeFromTotalBalanceYes.setChecked(false);
            }
        });

        hideYes = findViewById(R.id.hideYes);
        hideNo = findViewById(R.id.hideNo);

        hideYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideNo.setChecked(false);
            }
        });

        hideNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideYes.setChecked(false);
            }
        });

        cancelButton = findViewById(R.id.cancelButton);
        saveButton = findViewById(R.id.saveButton);

        value = null;

        newAccountMonthlyBudgetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcDialog.setValue(value);
                calcDialog.setSignCanBeChanged(false, 1);
                calcDialog.show(getFragmentManager(), "calc_dialog");
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountName = newAccountNameEditText.getText().toString();

                if(accountName.isEmpty()) {
                    Toast.makeText(getBaseContext(), "You must enter an account name.",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    String iconName = newAccountIconImageView.toString();
                    String startingBalance = newAccountStartingBalanceEditText.getText().toString();
                    String currency = newAccountCurrencyTextView.getText().toString();
                    String description = newAccountDescriptionEditText.getText().toString();
                    String monthlyBudget = newAccountMonthlyBudgetTextView.getText().toString();
                    String defaultTransactionStatus = newAccountDefaultTransactionStatusSpinner.getSelectedItem().toString();
                    String excludeFromTotalBalanceOption = "";

                    if (excludeFromTotalBalanceYes.isChecked()) {
                        excludeFromTotalBalanceOption = "Yes";
                    } else if (excludeFromTotalBalanceNo.isChecked()) {
                        excludeFromTotalBalanceOption = "No";
                    }

                    String hideOption = "";

                    if (hideYes.isChecked()) {
                        hideOption = "Yes";
                    } else if (hideNo.isChecked()) {
                        hideOption = "No";
                    }

                    db = new AdaptiveFinanceDatabaseHelper(getBaseContext());

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    String username = prefs.getString("CurrentUser", "");

                    insertNewAccountResult = db.insertNewAccount(username, accountName, iconName, startingBalance,
                            currency, description, monthlyBudget, defaultTransactionStatus,
                            excludeFromTotalBalanceOption, hideOption);

                    if (insertNewAccountResult) {
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(), "Inserting a new acccount failed, please try again.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    @Override
    public void onValueEntered(BigDecimal value) {
        // The calculator dialog returned a value
        newAccountMonthlyBudgetTextView.setText("" + value);
    }

    @SuppressWarnings("StatementWithEmptyBody")
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
            Intent intent = new Intent(this, TransactionsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_reports) {
            Intent intent = new Intent(this, ReportsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.new_account_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
