package com.daigler.adaptivefinance.data;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.daigler.adaptivefinance.OverviewIncomeVsExpenseFragment;
import com.daigler.adaptivefinance.data.DatabaseDescription.Users;
import com.daigler.adaptivefinance.data.DatabaseDescription.GeneralSettings;
import com.daigler.adaptivefinance.data.DatabaseDescription.OverviewFragmentOrder;
import com.daigler.adaptivefinance.data.DatabaseDescription.Accounts;
import com.daigler.adaptivefinance.data.DatabaseDescription.Transactions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdaptiveFinanceDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AdaptiveFinance.db";
    private static final int DATABASE_VERSION = 1;

    public AdaptiveFinanceDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_USERS_TABLE =
                "CREATE TABLE " + Users.TABLE_NAME + "(" +
                        Users._ID + " integer primary key, " +
                        Users.COLUMN_FULL_NAME + " TEXT, " +
                        Users.COLUMN_USER_NAME + " TEXT, " +
                        Users.COLUMN_PASSWORD + " TEXT, " +
                        Users.COLUMN_DATE_CREATED + " TEXT);";
        db.execSQL(CREATE_USERS_TABLE);

        final String CREATE_GENERAL_SETTINGS_TABLE =
                "CREATE TABLE " + GeneralSettings.TABLE_NAME + "(" +
                        GeneralSettings._ID + " integer primary key, " +
                        GeneralSettings.COLUMN_USER_NAME + " TEXT, " +
                        GeneralSettings.COLUMN_DEFAULT_CURRENCY + " TEXT, " +
                        GeneralSettings.COLUMN_DEFAULT_LANGUAGE + " TEXT);";
        db.execSQL(CREATE_GENERAL_SETTINGS_TABLE);

        final String CREATE_OVERVIEW_FRAGMENT_ORDER_TABLE =
                "CREATE TABLE " + OverviewFragmentOrder.TABLE_NAME + "(" +
                        OverviewFragmentOrder._ID + " integer primary key, " +
                        OverviewFragmentOrder.COLUMN_FRAGMENT_NAME + " TEXT, " +
                        OverviewFragmentOrder.COLUMN_FRAGMENT_VISIBILITY + " TEXT, " +
                        OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER + " TEXT);";
        db.execSQL(CREATE_OVERVIEW_FRAGMENT_ORDER_TABLE);

        final String CREATE_ACCOUNTS_TABLE =
                "CREATE TABLE " + Accounts.TABLE_NAME + "(" +
                        Accounts._ID + " integer primary key, " +
                        Accounts.COLUMN_USER_NAME + " TEXT, " +
                        Accounts.COLUMN_ACCOUNT_NAME + " TEXT, " +
                        Accounts.COLUMN_ICON_NAME + " TEXT, " +
                        Accounts.COLUMN_ACCOUNT_BALANCE + " TEXT, " +
                        Accounts.COLUMN_ACCOUNT_DESCRIPTION + " TEXT, " +
                        Accounts.COLUMN_ACCOUNT_NOTIFICATION + " TEXT, " +
                        Accounts.COLUMN_ACCOUNT_MONTHLY_BUDGET + " TEXT, " +
                        Accounts.COLUMN_DEFAULT_TRANSACTION_BALANCE + " TEXT, " +
                        Accounts.COLUMN_EXCLUDE_FROM_TOTAL_BALANCE + " TEXT, " +
                        Accounts.COLUMN_ACCOUNT_HIDE + " TEXT);";
        db.execSQL(CREATE_ACCOUNTS_TABLE);

        final  String CREATE_TRANSACTION_TABLE =
                "CREATE TABLE " + Transactions.TABLE_NAME + "(" +
                        Transactions._ID + " integer primary key, " +
                        Transactions.COLUMN_USER_NAME + " TEXT, " +
                        Transactions.COLUMN_ACCOUNT_ID + " TEXT, " +
                        Transactions.COLUMN_TRANSACTION_TYPE + " TEXT, " +
                        Transactions.COLUMN_TRANSACTION_AMOUNT + " TEXT, " +
                        Transactions.COLUMN_PAYEE_ITEM + " TEXT, " +
                        Transactions.COLUMN_CATEGORY + " TEXT, " +
                        Transactions.COLUMN_NOTES + " TEXT, " +
                        Transactions.COLUMN_TRANSACTION_DATE + " TEXT, " +
                        Transactions.COLUMN_REPEAT_CHECK +  " TEXT, " +
                        Transactions.COLUMN_REPEAT_DURATION + " TEXT, " +
                        Transactions.COLUMN_PERIOD_OPTION + " TEXT, " +
                        Transactions.COLUMN_TYPE_OF_REPEAT + " TEXT, " +
                        Transactions.COLUMN_REPEAT_FOR_DURATION + " TEXT, " +
                        Transactions.COLUMN_REPEAT_UNTIL_DATE + " TEXT, " +
                        Transactions.COLUMN_NEXT_TRANSACTION_DATE + " TEXT, " +
                        Transactions.COLUMN_STATUS + " TEXT, " +
                        Transactions.COLUMN_PROJECT + " TEXT);";
        db.execSQL(CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE " + Users.TABLE_NAME);
        db.execSQL("DROP TABLE " + GeneralSettings.TABLE_NAME);
        db.execSQL("DROP TABLE " + OverviewFragmentOrder.TABLE_NAME);
        db.execSQL("DROP TABLE " + Accounts.TABLE_NAME);
        db.execSQL("DROP TABLE " + Transactions.TABLE_NAME);

        onCreate(db);
    }

    public void insertOverviewFragmentOrder() {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues accountValues, latestTransactionValues, expenseByCategoryValues,
                incomeVsExpenseValues, highSpendingAlertsValues;

        accountValues = new ContentValues();

        accountValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_NAME, "Accounts Overview");
        accountValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER, "1");
        accountValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_VISIBILITY, "show");

        db.insert(OverviewFragmentOrder.TABLE_NAME, null, accountValues);

        latestTransactionValues = new ContentValues();

        latestTransactionValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_NAME,
                "Latest Transactions");
        latestTransactionValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER, "2");
        latestTransactionValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_VISIBILITY, "show");

        db.insert(OverviewFragmentOrder.TABLE_NAME, null, latestTransactionValues);

        expenseByCategoryValues = new ContentValues();

        expenseByCategoryValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_NAME,
                "Expense By Category");
        expenseByCategoryValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER, "3");
        expenseByCategoryValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_VISIBILITY, "show");

        db.insert(OverviewFragmentOrder.TABLE_NAME, null, expenseByCategoryValues);

        incomeVsExpenseValues = new ContentValues();

        incomeVsExpenseValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_NAME,
                "Income Vs Expense");
        incomeVsExpenseValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER, "4");
        incomeVsExpenseValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_VISIBILITY, "show");

        db.insert(OverviewFragmentOrder.TABLE_NAME, null, incomeVsExpenseValues);

        highSpendingAlertsValues = new ContentValues();

        highSpendingAlertsValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_NAME,
                "High Spending Alerts");
        highSpendingAlertsValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER, "5");
        highSpendingAlertsValues.put(OverviewFragmentOrder.COLUMN_FRAGMENT_VISIBILITY, "show");

        db.insert(OverviewFragmentOrder.TABLE_NAME, null, highSpendingAlertsValues);
    }

    public boolean fetchUsersByUsername(String username) {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT user_name FROM Users" +
                " WHERE user_name = '" + username + "'";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean registerNewUser(String fullname, String username, String password) {
        SQLiteDatabase db = getWritableDatabase();

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        ContentValues values = new ContentValues();
        values.put(Users.COLUMN_FULL_NAME, fullname);
        values.put(Users.COLUMN_USER_NAME, username);
        values.put(Users.COLUMN_PASSWORD, password);
        values.put(Users.COLUMN_DATE_CREATED, currentDate);

        long userRegistered = db.insert(Users.TABLE_NAME, null, values);

        if (userRegistered == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean loginUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = new String[] {
                Users.COLUMN_USER_NAME,
                Users.COLUMN_PASSWORD
        };

        String whereClause = "user_name = ? AND password = ?";

        String[] whereArgs = new String[] {
                username,
                password
        };

        Cursor cursor = db.query(Users.TABLE_NAME, columns, whereClause,
                whereArgs, null, null, null, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public int getUserID(String username) {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = new String[] { Users._ID };
        String whereClause = "user_name = ?";
        String[] whereArgs = new String[] { username };

        Cursor cursor = db.query(Users.TABLE_NAME, columns, whereClause,
                whereArgs, null, null, null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        else {
            return 0;
        }
    }

    public ArrayList<String> getOverviewFragmentOrder() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> overviewFragmentList = new ArrayList<>();
        String fragmentName;
        String fragmentOrder;
        String fragmentList;

        String[] columns = new String[] {
                OverviewFragmentOrder.COLUMN_FRAGMENT_NAME,
                OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER
        };

        Cursor cursor = db.query(OverviewFragmentOrder.TABLE_NAME, columns,
                null, null,null, null, null);

        if (cursor.moveToFirst()) {
            fragmentName = cursor.getString(0);
            fragmentOrder = cursor.getString(1);

            fragmentList = fragmentName + ":" + fragmentOrder;

            overviewFragmentList.add(fragmentList);

            while (cursor.moveToNext()) {
                fragmentName = cursor.getString(0);
                fragmentOrder = cursor.getString(1);

                fragmentList = fragmentName + ":" + fragmentOrder;

                overviewFragmentList.add(fragmentList);
            }
            return overviewFragmentList;
        } else {
            return null;
        }
    }

    public ArrayList<String> getOverviewFragmentNameList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> overviewFragmentList = new ArrayList<>();
        String fragmentName;
        String fragmentList;

        String[] columns = new String[] {
                OverviewFragmentOrder.COLUMN_FRAGMENT_NAME
        };

        Cursor cursor = db.query(OverviewFragmentOrder.TABLE_NAME, columns,
                null, null,null, null, null);

        if (cursor.moveToFirst()) {
            fragmentName = cursor.getString(0);
            overviewFragmentList.add(fragmentName);

            while (cursor.moveToNext()) {
                fragmentName = cursor.getString(0);
                overviewFragmentList.add(fragmentName);
            }
            return overviewFragmentList;
        } else {
            return null;
        }
    }


    public void updateOverviewFragmentOrder(String[] newList) {
        SQLiteDatabase db = getWritableDatabase();

        String fragmentName;
        String[] columns = new String[] {
                OverviewFragmentOrder.COLUMN_FRAGMENT_NAME,
                OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER
        };
        String whereClause = "fragment_name = ?";
        String[] whereArgs;

        for (int i = 0; i < newList.length; i++) {
            int order = i + 1;
            ContentValues values = new ContentValues();

            if(newList[i].equals("Accounts Overview")) {
                fragmentName = newList[i].toString();

                values.put(OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER, order);

                whereArgs = new String[] { fragmentName };

                db.update(OverviewFragmentOrder.TABLE_NAME, values,
                        whereClause, whereArgs);

            } else if (newList[i].equals("Latest Transactions")) {
                fragmentName = newList[i].toString();

                values.put(OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER, order);

                whereArgs = new String[] { fragmentName };

                db.update(OverviewFragmentOrder.TABLE_NAME, values,
                        whereClause, whereArgs);

            } else if (newList[i].equals("High Spending Alerts")) {
                fragmentName = newList[i].toString();

                values.put(OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER, order);

                whereArgs = new String[] { fragmentName };

                db.update(OverviewFragmentOrder.TABLE_NAME, values,
                        whereClause, whereArgs);
            } else if (newList[i].equals("Expense By Category")) {
                fragmentName = newList[i].toString();

                values.put(OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER, order);

                whereArgs = new String[] { fragmentName };

                db.update(OverviewFragmentOrder.TABLE_NAME, values,
                        whereClause, whereArgs);
            } else if (newList[i].equals("Income Vs Expense")) {
                fragmentName = newList[i].toString();

                values.put(OverviewFragmentOrder.COLUMN_FRAGMENT_ORDER, order);

                whereArgs = new String[] { fragmentName };

                db.update(OverviewFragmentOrder.TABLE_NAME, values,
                        whereClause, whereArgs);
            }
        }
    }

    public String retrieveAccounts() {
        SQLiteDatabase db = getReadableDatabase();
        return "test";
    }

    public boolean insertNewAccount(String username, String accountName, String iconName,
                                    String startingBalance, String currency, String description,
                                    String monthlyBudget, String defaultTransactionStatus,
                                    String excludeFromTotalBalanceOption, String hideOption) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Accounts.COLUMN_USER_NAME, username);
        values.put(Accounts.COLUMN_ACCOUNT_NAME, accountName);
        values.put(Accounts.COLUMN_ICON_NAME, iconName);
        values.put(Accounts.COLUMN_ACCOUNT_BALANCE, startingBalance);
        values.put(Accounts.COLUMN_ACCOUNT_DESCRIPTION, description);
        values.put(Accounts.COLUMN_ACCOUNT_NOTIFICATION, "No");
        values.put(Accounts.COLUMN_ACCOUNT_MONTHLY_BUDGET, monthlyBudget);
        values.put(Accounts.COLUMN_DEFAULT_TRANSACTION_BALANCE, defaultTransactionStatus);
        values.put(Accounts.COLUMN_EXCLUDE_FROM_TOTAL_BALANCE, excludeFromTotalBalanceOption);
        values.put(Accounts.COLUMN_ACCOUNT_HIDE, hideOption);

        long query = db.insert(Accounts.TABLE_NAME, null, values);

        if (query == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getNumberOfActiveAccounts(String username) {
        SQLiteDatabase db = getReadableDatabase();
        int rowCount;
        long resultRows;


        String whereClause = "user_name = ? AND hide = ?";
        String[] whereArgs = {
                username,
                "No"
        };

        resultRows = DatabaseUtils.queryNumEntries(db, Accounts.TABLE_NAME, whereClause,
                whereArgs);

        rowCount = (int)resultRows;
        return rowCount;
    }

    public int getNumberOfHiddenAccounts(String username) {
        SQLiteDatabase db = getReadableDatabase();
        int rowCount;
        long resultRows;


        String whereClause = "user_name = ? AND hide = ?";
        String[] whereArgs = {
                username,
                "Yes"
        };

        resultRows = DatabaseUtils.queryNumEntries(db, Accounts.TABLE_NAME, whereClause,
                whereArgs);

        rowCount = (int)resultRows;
        return rowCount;
    }

    /*public ArrayList<String> retrieveAccounts() {
        SQLiteDatabase db = getReadableDatabase();
        return null;
    };*/

    public boolean deleteAccount() {
        SQLiteDatabase db = getWritableDatabase();
        return true;
    }

    public boolean addTransaction() {
        SQLiteDatabase db = getWritableDatabase();
        return true;
    }

    public boolean deleteTransaction() {
        SQLiteDatabase db = getWritableDatabase();
        return true;
    }

    public String getNetBalance() {
        SQLiteDatabase db = getReadableDatabase();
        return "0.00";
    }
}