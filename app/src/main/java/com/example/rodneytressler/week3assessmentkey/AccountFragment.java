package com.example.rodneytressler.week3assessmentkey;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rodneytressler on 12/14/17.
 */

public class AccountFragment extends Fragment {

    @BindView(R.id.name_input)
    protected EditText nameInput;

    @BindView(R.id.class_input)
    protected EditText classInput;
    private String mage = "mage";
    private String archer = "archer";
    private String warrior = "warrior";

    private AccountFragmentCallback accountFragmentCallback;

    @OnClick(R.id.button_finish)
    protected void onFinishButtonClicked(View view) {
        String name = nameInput.getText().toString();
        String aClass = classInput.getText().toString();

        if (name.isEmpty() || aClass.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Please fill out all fields");
            builder.setTitle("Attention");
            builder.setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();

        } else if (aClass.equals(mage) || aClass.equals(warrior) || aClass.equals(archer)) {
            accountFragmentCallback.createAccount(name, aClass);

        } else {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
            alertBuilder.setTitle("Attention").setMessage("Please enter one of the Following Classes -\n" +
                    "mage   " +
                    "archer   " +
                    "warrior").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_creation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static AccountFragment newInstance() {

        Bundle args = new Bundle();

        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void showAlertDialog(String message) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void setCallback(AccountFragmentCallback accountFragmentCallback) {
        this.accountFragmentCallback = accountFragmentCallback;

    }

    public interface AccountFragmentCallback {
        void createAccount(String name, String aClass);
    }
}
