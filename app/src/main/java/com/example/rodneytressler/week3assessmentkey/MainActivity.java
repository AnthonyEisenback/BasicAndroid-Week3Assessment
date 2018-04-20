package com.example.rodneytressler.week3assessmentkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AccountFragment.AccountFragmentCallback{

    @BindView(R.id.welcome_text)
    protected TextView welcomeText;
    private AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);




    }

    @Override
    protected void onStart() {
        super.onStart();
        accountFragment = accountFragment.newInstance();
        accountFragment.setCallback(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, accountFragment).commit();

    }

    @Override
    public void createAccount(String name, String aClass) {

        welcomeText.setText(getString(R.string.welcome_text, name, aClass));
        getSupportFragmentManager().beginTransaction().remove(accountFragment).commit();

    }
}
