package com.daigler.adaptivefinance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

public class OverviewAccountsCompactViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.overview_accounts_compact_container, container,
                false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.accountCompactToolbar);

        toolbar.setTitle("My Accounts");
        toolbar.inflateMenu(R.menu.accounts_compact_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.action_compact_account_hide:
                        FrameLayout containerToHide = getActivity().findViewById(R.id.container_1);
                        containerToHide.setVisibility(View.GONE);
                        return true;
                    case R.id.action_compact_account_reorder:
                        Intent intent = new Intent(getActivity(), OverviewCustomizationActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_compact_account_reorder_account:
                        Toast.makeText(getContext(), "Under Construction.",
                                Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.action_compact_account_new_account:
                        Intent newAccountIntent = new Intent(getActivity(), NewAccountActivity.class);
                        startActivity(newAccountIntent);
                        return true;
                    case R.id.action_compact_account_switch_to_list_mode:
                        Fragment fragment = new OverviewAccountsListViewFragment();

                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.accountOverviewListViewFragment, fragment);
                        transaction.commit();
                        return true;
                }
                return false;
            }
        });

        return view;
    }
}
