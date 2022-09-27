package com.muhammadtalhaminhas.project;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.ViewHolder>{

    private ArrayList<String> skills;
    public SkillsAdapter(ArrayList<String> skills) {
        this.skills=skills;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.skill_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.skill.setText(skills.get(position));
    }

    @Override
    public int getItemCount() {
        return this.skills.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView skill;
        public Button delete;
        public ViewHolder(View itemView) {
            super(itemView);
            this.skill = itemView.findViewById(R.id.skill);
            this.delete = itemView.findViewById(R.id.delete);
        }
    }
    public void deleteItem(int position) {
        skills.remove(position);
        notifyItemRemoved(position);
    }
    }
