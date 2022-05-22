package ru.mirea.burmakin.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculate extends Fragment implements View.OnClickListener {

    EditText etNum1;
    EditText etNum2;

    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button btnDiv;

    TextView tvResult;

    String oper = "";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Calculate() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Calculate newInstance(String param1, String param2) {
        Calculate fragment = new Calculate();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_calculate, null);
        etNum1 = (EditText) v.findViewById(R.id.etNum1);
        etNum2 = (EditText) v.findViewById(R.id.etNum2);

        btnAdd = (Button) v.findViewById(R.id.btnAdd);
        btnSub = (Button) v.findViewById(R.id.btnSub);
        btnMult = (Button) v.findViewById(R.id.btnMult);
        btnDiv = (Button) v.findViewById(R.id.btnDiv);

        tvResult = (TextView) v.findViewById(R.id.tvResult);

        // прописываем обработчик
        btnAdd.setOnClickListener(v14 -> {
            // TODO Auto-generated method stub
            float num1 = 0;
            float num2 = 0;
            float result = 0;

            if (TextUtils.isEmpty(etNum1.getText().toString())
                    || TextUtils.isEmpty(etNum2.getText().toString())) {
                return;
            }

            num1 = Float.parseFloat(etNum1.getText().toString());
            num2 = Float.parseFloat(etNum2.getText().toString());
            oper = "+";
            result = num1 + num2;

            tvResult.setText(num1 + " " + oper + " " + num2 + " = " + result);
        });
        btnSub.setOnClickListener(v13 -> {
            // TODO Auto-generated method stub
            float num1 = 0;
            float num2 = 0;
            float result = 0;

            if (TextUtils.isEmpty(etNum1.getText().toString())
                    || TextUtils.isEmpty(etNum2.getText().toString())) {
                return;
            }

            num1 = Float.parseFloat(etNum1.getText().toString());
            num2 = Float.parseFloat(etNum2.getText().toString());
            oper = "-";
            result = num1 - num2;

            tvResult.setText(num1 + " " + oper + " " + num2 + " = " + result);
        });
        btnMult.setOnClickListener(v12 -> {
            // TODO Auto-generated method stub
            float num1 = 0;
            float num2 = 0;
            float result = 0;

            if (TextUtils.isEmpty(etNum1.getText().toString())
                    || TextUtils.isEmpty(etNum2.getText().toString())) {
                return;
            }

            num1 = Float.parseFloat(etNum1.getText().toString());
            num2 = Float.parseFloat(etNum2.getText().toString());
            oper = "*";
            result = num1 * num2;
            tvResult.setText(num1 + " " + oper + " " + num2 + " = " + result);
        });
        btnDiv.setOnClickListener(v1 -> {
            // TODO Auto-generated method stub
            float num1 = 0;
            float num2 = 0;
            float result = 0;

            if (TextUtils.isEmpty(etNum1.getText().toString())
                    || TextUtils.isEmpty(etNum2.getText().toString())) {
                return;
            }

            num1 = Float.parseFloat(etNum1.getText().toString());
            num2 = Float.parseFloat(etNum2.getText().toString());
            oper = "/";
            result = num1 / num2;
            tvResult.setText(num1 + " " + oper + " " + num2 + " = " + result);
        });
        return v;
    }

    @Override
    public void onClick(View view) {
    }
}