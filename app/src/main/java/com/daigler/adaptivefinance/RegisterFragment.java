package com.daigler.adaptivefinance;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daigler.adaptivefinance.data.AdaptiveFinanceDatabaseHelper;

public class RegisterFragment extends Fragment {

    private AdaptiveFinanceDatabaseHelper db;
    private boolean addingNewUser = true;

    private TextInputLayout fullNameTextInputLayout;
    private TextInputLayout userNameTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private TextInputLayout confirmPasswordInputLayout;

    private Button registerButton;
    private TextView signInLinkTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(
                R.layout.register_fragment, container, false);

        fullNameTextInputLayout = (TextInputLayout) view.findViewById(
                R.id.fullNameRegisterTextInputLayout);
        userNameTextInputLayout = (TextInputLayout) view.findViewById(
                R.id.usernameRegisterTextInputLayout);
        passwordTextInputLayout = (TextInputLayout) view.findViewById(
                R.id.passwordRegisterTextInputLayout);
        confirmPasswordInputLayout = (TextInputLayout) view.findViewById(
                R.id.confirmPasswordRegisterTextInputLayout);

        registerButton = (Button) view.findViewById(R.id.registerButton);
        signInLinkTextView = (TextView) view.findViewById(R.id.signInLinkTextView);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = fullNameTextInputLayout.getEditText().getText().toString();
                String username = userNameTextInputLayout.getEditText().getText().toString();
                String password = passwordTextInputLayout.getEditText().getText().toString();
                String cPassword = confirmPasswordInputLayout.getEditText().getText().toString();

                if (fullname.length() > 0 && username.length() > 0 &&
                        password.length() > 0 && cPassword.length() > 0) {

                    if (password.equals(cPassword)) {
                        registerNewUser(fullname, username, password);
                    }
                    else {
                        Toast.makeText(getActivity(), "Passwords do not match.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Please fill missing fields.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        signInLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment loginFragment = new LoginFragment();

                FragmentTransaction transaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.loginRegisterContainer, loginFragment);
                transaction.disallowAddToBackStack();
                transaction.commit();
            }
        });

        return view;
    }

    private void registerNewUser(String fullname, String username, String password) {
        if (addingNewUser) {
            db = new AdaptiveFinanceDatabaseHelper(getContext());

            Boolean userAlreadyExists, registeredResults;

            userAlreadyExists = db.fetchUsersByUsername(username);

            if(!userAlreadyExists) {
                registeredResults = db.registerNewUser(fullname, username, password);

                if(registeredResults) {
                    Toast.makeText(getActivity(), "Successfully registered user! Sign in now.",
                            Toast.LENGTH_LONG).show();

                    LoginFragment loginFragment = new LoginFragment();

                    FragmentTransaction transaction =
                            getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.loginRegisterContainer, loginFragment);
                    transaction.disallowAddToBackStack();
                    transaction.commit();
                } else {
                    Toast.makeText(getActivity(), "User was not successfully registered, try again..",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
               Toast.makeText(getActivity(), "User with this username already exists.",
                       Toast.LENGTH_SHORT).show();
           }
        }
    }
}
