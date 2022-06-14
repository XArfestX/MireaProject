package ru.mirea.burmakin.mireaproject;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link History#newInstance} factory method to
 * create an instance of this fragment.
 */
public class History extends Fragment {
    private FloatingActionButton addHistory;
    private FloatingActionButton savehistory;
    private RecyclerView recyclerView;
    private EditText historyname;
    private EditText history;
    private TextView textView;
    private boolean iscreate = false;
    private Animation fab_mini_show;
    private Animation fab_mini_hide;
    ArrayList<HistoryObj> historylist = new ArrayList<HistoryObj>();
    final String STORAGE_NAME = "StorageName";
    static final File dir = new File("/data/data/ru.mirea.burmakin.mireaproject/files");
    static final String[] EXTENSIONS = new String[]{
            "txt" // and other formats you need
    };
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private SharedPreferences preferences;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static final FilenameFilter TEXT = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    public History() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static History newInstance(String param1, String param2) {
        History fragment = new History();
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
        View v = inflater.inflate(R.layout.fragment_history, null);
        fab_mini_show= AnimationUtils.loadAnimation(getActivity(),R.anim.fab_mini_show);
        fab_mini_hide= AnimationUtils.loadAnimation(getActivity(),R.anim.fab_mini_hide);
        savehistory=v.findViewById(R.id.fab_mini);
        addHistory = v.findViewById(R.id.floatingActionButton);
        historyname = v.findViewById(R.id.HistoryName);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setVisibility(v.VISIBLE);
        history = v.findViewById(R.id.history);
        historyname.setVisibility(v.GONE);
        history.setVisibility(v.GONE);
        textView =v.findViewById(R.id.textView2);
        textView.setText("Ваши Истории");
        preferences = getActivity().getPreferences(MODE_PRIVATE);




        FileOutputStream outputStream;
        try {
            setInitialData(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // создаем адаптер
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HistoryAdapter adapter = new HistoryAdapter(getActivity(),historylist);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        addHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iscreate) {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) savehistory.getLayoutParams();
                    layoutParams.bottomMargin -= (int) (savehistory.getHeight() * 0.25);
                    savehistory.setLayoutParams(layoutParams);
                    savehistory.startAnimation(fab_mini_hide);
                    savehistory.setClickable(false);
                    savehistory.setVisibility(v.GONE);
                    addHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_create));
                    recyclerView.setVisibility(v.VISIBLE);
                    historyname.setVisibility(v.GONE);
                    history.setVisibility(v.GONE);
                    textView.setText("Ваши Истории");
                    iscreate=false;

                } else {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) savehistory.getLayoutParams();
                    addHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_cancel));
                    layoutParams.bottomMargin += (int) (savehistory.getHeight() * 0.25);
                    savehistory.setLayoutParams(layoutParams);
                    savehistory.startAnimation(fab_mini_show);
                    savehistory.setClickable(true);
                    savehistory.setVisibility(v.VISIBLE);
                    recyclerView.setVisibility(v.GONE);
                    textView.setText("Создание новой истории");
                    history.setVisibility(v.VISIBLE);
                    historyname.setVisibility(v.VISIBLE);
                    iscreate=true;
                }
            }
        });
        savehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream outputStream;

                Toast.makeText(getActivity(), "History saved", Toast.LENGTH_SHORT).show();
                String fileName = (historyname.getText())+".txt";
                try {
                    outputStream = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
                    outputStream.write(history.getText().toString().getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) savehistory.getLayoutParams();
                layoutParams.bottomMargin -= (int) (savehistory.getHeight() * 0.25);
                savehistory.setLayoutParams(layoutParams);
                savehistory.startAnimation(fab_mini_hide);
                savehistory.setClickable(false);
                savehistory.setVisibility(v.GONE);
                addHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_create));
                recyclerView.setVisibility(v.VISIBLE);
                historyname.setVisibility(v.GONE);
                history.setVisibility(v.GONE);
                textView.setText("Ваши Истории");
                historylist.clear();
                try {
                    setInitialData(dir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // создаем адаптер
                adapter.notifyDataSetChanged();
                // устанавливаем для списка адаптер
                historyname.setText("");
                history.setText("");
                iscreate=false;
            }});

        return v;
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    private void setInitialData(final File folder) throws IOException {
        if (dir.isDirectory()) { // make sure it's a directory
            for (final File fileEntry : dir.listFiles(TEXT)) {
                if (fileEntry.isDirectory()) {
                    setInitialData(fileEntry);
                } else {
                    FileInputStream fin = null;
                    fin = getActivity().openFileInput(fileEntry.getName());
                    byte[] bytes = new byte[fin.available()];
                    fin.read(bytes);
                    String text = new String (bytes);
                    String text2 = preferences.getString(STORAGE_NAME, "Название профиля");
                    HistoryObj historyitem = new HistoryObj(fileEntry.getName().toString(), text2,text);
                    historylist.add(historyitem);

                }

            }
        }
    }
    /* Проверяем внешнее хранилище на доступность чтения */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}