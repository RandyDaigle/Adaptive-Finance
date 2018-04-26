package com.daigler.adaptivefinance;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class OverviewHighSpendingAlertsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.overview_high_spending_alerts_container, container,
                false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.highSpendingAlertsToolbar);

        toolbar.setTitle("High Spending Alerts");
        toolbar.inflateMenu(R.menu.high_spending_alerts_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.action_high_spending_alerts_hide:
                        FrameLayout containerToHide = getActivity().findViewById(R.id.container_5);
                        containerToHide.setVisibility(View.GONE);
                        return true;
                }
                return false;
            }
        });
        return view;
    }
}
