package com.dreamisi.moviebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import data.models.Actor

class ActorsListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var actors = listOf<Actor>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ActorListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ActorListViewHolder -> holder.onBind(actors[position])
        }
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
    }

    private class ActorListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val actor_image: ImageView = itemView.findViewById(R.id.actor_ph)
        private val actor_name: TextView = itemView.findViewById(R.id.actor_name)

        fun onBind(actor: Actor) {
            actor_image.setImageResource(actor.image)
            actor_name.text = actor.name
        }
    }

}