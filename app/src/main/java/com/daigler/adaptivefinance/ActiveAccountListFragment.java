package com.daigler.adaptivefinance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toolbar;

import com.daigler.adaptivefinance.data.AdaptiveFinanceDatabaseHelper;

public class ActiveAccountListFragment extends Fragment {
    private AdaptiveFinanceDatabaseHelper db;

    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_accounts_list, container,
                false);

        Toolbar toolbar = view.findViewById(R.id.userAccountsListViewToolbar);
        toolbar.setTitle("CAD Accounts (Default)");
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setSubtitle("Total Balance (CAD): $0.00");
        toolbar.inflateMenu(R.menu.user_accounts_listview_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.action_user_accounts_listview_reorder_account:
                        break;
                }
                return false;
            }
        });

        return view;
    }
}
