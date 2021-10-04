package com.example.mynotes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    public static class NotesViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout containerView;
        private TextView textView;
        NotesViewHolder(View view){
            super(view);
            containerView = view.findViewById(R.id.note_row);
            textView = view.findViewById(R.id.note_row_text);
            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Note current = (Note)containerView.getTag();
                    Intent intent = new Intent(view.getContext(),NoteActivity.class);
                    intent.putExtra("id",current.id);
                    intent.putExtra("contents",current.contents);
                    view.getContext().startActivity(intent);
                }
            });
        }

    }
    List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row,
                parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
     Note current = notes.get(position);
     holder.textView.setText(current.contents);
     holder.containerView.setTag(current);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    public void reload(){
    notes = MainActivity.database.noteDao().getAllNotes();
    notifyDataSetChanged();
    }
}
