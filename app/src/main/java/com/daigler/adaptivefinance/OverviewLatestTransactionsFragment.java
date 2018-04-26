package com.daigler.adaptivefinance;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

public class OverviewLatestTransactionsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.overview_latest_transactions_container, container,
                false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.recentTransactionsToolbar);

        toolbar.setTitle("Recent Transactions");
        toolbar.inflateMenu(R.menu.recent_transactions_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.action_recent_transaction_search:
                        return true;
                    case R.id.action_recent_transaction_hide:
                        FrameLayout containerToHide = getActivity().findViewById(R.id.container_2);
                        containerToHide.setVisibility(View.GONE);
                        return true;
                    case R.id.action_recent_transaction_reorder:
                        Intent intent = new Intent(getActivity(), OverviewCustomizationActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.recent_transactions_menu, menu);
    }
}
