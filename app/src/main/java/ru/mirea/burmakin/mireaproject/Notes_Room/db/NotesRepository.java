package ru.mirea.burmakin.mireaproject.Notes_Room.db;

import android.app.Application;
import android.os.AsyncTask;

public class NotesRepository {
    private MainDAO mainDAO;
    private RoomDB roomDB;

    public NotesRepository(Application application){
        roomDB = RoomDB.getInstance(application);
        mainDAO = roomDB.mainDao();
    }


    public void deleteNote(final Notes notes){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                roomDB.mainDao().delete(notes);
                return null;
            }
        }.execute();
    }
}
