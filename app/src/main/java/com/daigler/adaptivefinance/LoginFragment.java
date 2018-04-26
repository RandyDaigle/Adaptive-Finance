package com.daigler.adaptivefinance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daigler.adaptivefinance.data.AdaptiveFinanceDatabaseHelper;

public class LoginFragment extends Fragment {

    private AdaptiveFinanceDatabaseHelper db;
    private boolean fetchedUser;

    private EditText usernameTextView;
    private EditText passwordTextView;

    private Button signInButton;
    private TextView registerLinkTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(
                R.layout.login_fragment, container, false);

        signInButton = view.findViewById(R.id.signInButton);
        registerLinkTextView = (TextView) view.findViewById(R.id.registerLinkTextView);

        usernameTextView = (EditText) view.findViewById(R.id.usernameLoginTextView);
        passwordTextView = (EditText) view.findViewById(R.id.passwordLoginTextView);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameTextView.getText().toString();
                String password = passwordTextView.getText().toString();


                if(userName.length() > 0 && password.length() > 0) {
                    db = new AdaptiveFinanceDatabaseHelper(getContext());

                    fetchedUser = checkCredentials(userName, password);

                    if (fetchedUser) {
                            int userID = db.getUserID(userName);

                            if (userID != 0) {
                                Intent intent = new Intent(getActivity(), IntroActivity.class);
                                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("CurrentUser", userName);
                                editor.commit();
                                /*Bundle userInfo = new Bundle();
                                userInfo.putInt("UserID", userID);
                                intent.putExtras(userInfo);*/
                                startActivity(intent);
                            } else {
                                Toast.makeText(getActivity(), "Failed to fetch user account. Try again.",
                                        Toast.LENGTH_SHORT).show();
                            }
                    }
                    else {
                        Toast.makeText(getActivity(), "Incorrect username and/or password combination." +
                                "Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (userName.isEmpty() && password.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a username and password.",
                            Toast.LENGTH_SHORT).show();
                }
                else if (userName.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a username.",
                            Toast.LENGTH_SHORT).show();
                }
                else if (password.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a password.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment registerFragment = new RegisterFragment();

                FragmentTransaction transaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.loginRegisterContainer, registerFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    private boolean checkCredentials(String username, String password) {
        fetchedUser = db.loginUser(username, password);

        if (fetchedUser)
            return true;
        else
            return false;
    }
}
