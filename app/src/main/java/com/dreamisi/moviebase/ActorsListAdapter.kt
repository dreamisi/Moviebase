package com.dreamisi.moviebase

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import data.models.Actor

class ActorsListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var actors = listOf<Actor>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return actors.size
    }


}