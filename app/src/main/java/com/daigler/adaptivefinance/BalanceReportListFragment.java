package com.daigler.adaptivefinance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

public class BalanceReportListFragment extends Fragment {

    private FrameLayout dailyBalanceLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.balance_reports_list, container,
                false);

        dailyBalanceLayout = view.findViewById(R.id.dailyBalanceOption);

        dailyBalanceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Under construction.", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
