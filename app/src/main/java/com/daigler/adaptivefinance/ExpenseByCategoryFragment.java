package com.daigler.adaptivefinance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

public class ExpenseByCategoryFragment extends Fragment {

    private FrameLayout expenseByCategoryLayout;
    private FrameLayout dailyExpenseLayout;
    private FrameLayout monthlyExpenseLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.expense_reports_list, container,
                false);

        expenseByCategoryLayout = view.findViewById(R.id.expenseByCategoryOption);
        dailyExpenseLayout = view.findViewById(R.id.dailyExpenseOption);
        monthlyExpenseLayout = view.findViewById(R.id.monthlyExpenseOption);

        expenseByCategoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Under construction.", Toast.LENGTH_LONG).show();
            }
        });

        dailyExpenseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Under construction.", Toast.LENGTH_LONG).show();
            }
        });

        monthlyExpenseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Under construction.", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
