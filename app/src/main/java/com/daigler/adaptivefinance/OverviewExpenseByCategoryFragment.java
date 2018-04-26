package com.daigler.adaptivefinance;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class OverviewExpenseByCategoryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.overview_expense_by_category_container, container,
                false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.expenseByCategoryToolbar);

        toolbar.setTitle("Expense By Category");
        toolbar.inflateMenu(R.menu.expense_by_category_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.action_expense_by_category_hide:
                        FrameLayout containerToHide = getActivity().findViewById(R.id.container_3);
                        containerToHide.setVisibility(View.GONE);
                        return true;
                    case R.id.action_expense_by_category_reorder:
                        Intent intent = new Intent(getActivity(), OverviewCustomizationActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_expense_by_category_settings:
                        return true;
                }
                return false;
            }
        });

        return view;
    }
}
