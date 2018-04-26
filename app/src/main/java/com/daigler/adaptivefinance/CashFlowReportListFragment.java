package com.daigler.adaptivefinance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

public class CashFlowReportListFragment extends Fragment {

    private FrameLayout incomeVsExpenseLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.cash_flow_reports_list, container,
                false);

        incomeVsExpenseLayout = view.findViewById(R.id.incomeVsExpenseOption);

        incomeVsExpenseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Under construction.", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
