package com.daigler.adaptivefinance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

public class IncomeReportListFragment extends Fragment {

    private FrameLayout dailyIncomeLayout;
    private FrameLayout monthlyIncomeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.income_reports_list, container,
                false);

        dailyIncomeLayout = view.findViewById(R.id.dailyIncomeOption);
        monthlyIncomeLayout = view.findViewById(R.id.monthlyIncomeOption);

        dailyIncomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Under construction.", Toast.LENGTH_LONG).show();
            }
        });

        monthlyIncomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Under construction.", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
