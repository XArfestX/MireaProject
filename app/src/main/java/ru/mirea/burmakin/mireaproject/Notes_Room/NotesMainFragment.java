package ru.mirea.burmakin.mireaproject.Notes_Room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ru.mirea.burmakin.mireaproject.R;
import ru.mirea.burmakin.mireaproject.Notes_Room.db.Notes;
import ru.mirea.burmakin.mireaproject.Notes_Room.db.RoomDB;
import ru.mirea.burmakin.mireaproject.Notes_Room.db.ViewModel;

public class NotesMainFragment extends Fragment implements NotesListAdapter.ItemClicked{ //implements NotesListAdapter.OnNoteListener
    RecyclerView recyclerView;
    FloatingActionButton fab_add_notes;

    //    private ArrayList<Notes> mNotes = new ArrayList<>();
    private NotesListAdapter notesListAdapter;
    private ViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab_add_notes=view.findViewById(R.id.fab_add_notes);


        fab_add_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_notesMainFragment_to_notesTalkerFragment);

            }
        });

        recyclerView=view.findViewById(R.id.recycler_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        notesListAdapter = new NotesListAdapter(this.getContext(), this);
        recyclerView.setAdapter(notesListAdapter);

        loadNotesList();

    }


    private void loadNotesList(){
        RoomDB database = RoomDB.getInstance(this.getContext());
        List<Notes> notesList =  database.mainDao().getAll();
        notesListAdapter.setNotesList(notesList);
    }

    @Override
    public void deletedClicked(Notes notes) {
        viewModel.deleteNote(notes);
    }
}