package ru.mirea.burmakin.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Setting extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static final String STORAGE_NAME = "StorageName";
    public static final String APP_PREFERENCES_NICKNAME = "Nickname";
    public static final String APP_PREFERENCES_AGE = "Age";

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;
    private EditText profileName;
    private EditText age;
    private Button save;
    private Button get;


    public Setting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Setting.
     */
    // TODO: Rename and change types and number of parameters
    public static Setting newInstance(String param1, String param2) {
        Setting fragment = new Setting();
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
        View v = inflater.inflate(R.layout.fragment_setting, null);

        settings = getActivity().getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        profileName = v.findViewById(R.id.profile);
        age = v.findViewById(R.id.age);
        save = v.findViewById(R.id.save);
        get = v.findViewById(R.id.get);


        save.setOnClickListener(View -> {
            String strNickName = profileName.getText().toString(); // здесь содержится текст, введенный в текстовом поле
            String strAge = age.getText().toString();


            editor.putString(APP_PREFERENCES_NICKNAME, strNickName);
            editor.putString(APP_PREFERENCES_AGE, strAge);
            editor.apply();
        });

        get.setOnClickListener(View -> {
            if(settings.contains(APP_PREFERENCES_NICKNAME)) {
                profileName.setText(settings.getString(APP_PREFERENCES_NICKNAME, ""));
            }
            if(settings.contains(APP_PREFERENCES_AGE)) {
                age.setText(settings.getString(APP_PREFERENCES_AGE, ""));
            }
        });



        return v;
    }

    public static void init( Context cntxt ){
        context = cntxt;
    }
    private static void init(){
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }
    public static void addProperty( String name, String value ){
        if( settings == null ){
            init();
        }
        editor.putString( name, value );
        editor.apply();
    }

    public static String getProperty( String name ){
        if( settings == null ){
            init();
        }
        return settings.getString( name, null );
    }

}
