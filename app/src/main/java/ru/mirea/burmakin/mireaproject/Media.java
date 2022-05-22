package ru.mirea.burmakin.mireaproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Media extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    Button btnStart;
    Button btnStop;

    public Media() {
        // Required empty public constructor
    }

    public static Media newInstance(String param1, String param2) {
        Media fragment = new Media();
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
        View v = inflater.inflate(R.layout.fragment_media, null);

        btnStart = (Button) v.findViewById(R.id.btnStart);
        btnStop = (Button) v.findViewById(R.id.btnStop);
        btnStart.setOnClickListener(view -> getActivity().startService(new Intent(getActivity(),MyService.class)));
        btnStop.setOnClickListener(view -> getActivity().stopService(new Intent(getActivity(),MyService.class)));

        return v;
    }
}