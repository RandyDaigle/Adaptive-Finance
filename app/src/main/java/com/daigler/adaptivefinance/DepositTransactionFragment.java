package com.daigler.adaptivefinance;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DepositTransactionFragment extends Fragment {

    private TextView depositDateTextView;
    private TextView untilDateTextView;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.new_transaction_fragment, container,
                false);

        depositDateTextView = view.findViewById(R.id.depositDateTextView);

        depositDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), dateSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        untilDateTextView = view.findViewById(R.id.untilDateTextView);

        untilDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        dateSetListener, year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String mt;
                String date;

                if (month == 0) {
                    mt = "Jan";
                } else if (month == 1) {
                    mt = "Feb";
                } else if (month == 2) {
                    mt = "Mar";
                } else if (month == 3) {
                    mt = "Apr";
                } else if (month == 4) {
                    mt = "May";
                } else if (month == 5) {
                    mt = "Jun";
                } else if (month == 6) {
                    mt = "Jul";
                } else if (month == 7) {
                    mt = "Aug";
                } else if (month == 8) {
                    mt = "Sep";
                } else if (month == 9) {
                    mt = "Oct";
                } else if (month == 10) {
                    mt = "Nov";
                } else {
                    mt = "Dec";
                }

                date = dayOfMonth + "." + mt + "." + year;
                depositDateTextView.setText(date);
            }
        };

        return view;
    }

}
