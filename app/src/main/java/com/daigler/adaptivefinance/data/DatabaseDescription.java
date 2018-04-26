package com.daigler.adaptivefinance.data;

import android.provider.BaseColumns;

public class DatabaseDescription {
    public static final String AUTHORITY =
            "com.daigler.adaptivefinance.data";

    public static final class Users implements BaseColumns {
        public static final String TABLE_NAME = "Users";

        public static final String COLUMN_FULL_NAME = "full_name";
        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_DATE_CREATED = "date_created";
    }

    public static final class GeneralSettings implements BaseColumns {
        public static final String TABLE_NAME = "General_Settings";

        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_DEFAULT_LANGUAGE = "default_language";
        public static final String COLUMN_DEFAULT_CURRENCY = "default_currency";
    }

    public static final class OverviewFragmentOrder implements BaseColumns {
        public static final String TABLE_NAME = "Overview_Fragment_Order";

        public static final String COLUMN_FRAGMENT_NAME = "fragment_name";
        public static final String COLUMN_FRAGMENT_VISIBILITY = "fragment_visibility";
        public static final String COLUMN_FRAGMENT_ORDER = "fragment_order";
    }

    public static final class Accounts implements BaseColumns {
        public static final String TABLE_NAME = "Accounts";

        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_ACCOUNT_NAME = "account_name";
        public static final String COLUMN_ICON_NAME = "icon_name";
        public static final String COLUMN_ACCOUNT_BALANCE = "account_balance";
        public static final String COLUMN_ACCOUNT_DESCRIPTION = "account_description";
        public static final String COLUMN_ACCOUNT_NOTIFICATION = "notifications";
        public static final String COLUMN_ACCOUNT_MONTHLY_BUDGET = "monthly_budget";
        public static final String COLUMN_DEFAULT_TRANSACTION_BALANCE = "default_transaction_balance";
        public static final String COLUMN_EXCLUDE_FROM_TOTAL_BALANCE = "exclude_from_total_balance";
        public static final String COLUMN_ACCOUNT_HIDE = "hide";
    }

    public static final class Transactions implements BaseColumns {
        public static final String TABLE_NAME = "Transactions";

        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_ACCOUNT_ID = "account_id";
        public static final String COLUMN_TRANSACTION_TYPE = "transaction_type";
        public static final String COLUMN_TRANSACTION_AMOUNT = "transaction_amount";
        public static final String COLUMN_PAYEE_ITEM = "payee_item";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_NOTES = "notes";
        public static final String COLUMN_TRANSACTION_DATE = "transaction_date";
        public static final String COLUMN_REPEAT_CHECK = "repeat_check";
        public static final String COLUMN_REPEAT_DURATION = "repeat_duration";
        public static final String COLUMN_PERIOD_OPTION = "period_option";
        public static final String COLUMN_TYPE_OF_REPEAT = "type_of_repeat";
        public static final String COLUMN_REPEAT_FOR_DURATION = "repeat_for_duration";
        public static final String COLUMN_REPEAT_UNTIL_DATE = "repeat_until_date";
        public static final String COLUMN_NEXT_TRANSACTION_DATE = "next_transaction_date";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_PROJECT = "project";
    }
}
