package com.dreamisi.moviebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dreamisi.moviebase.data.models.Actor

class ActorsListAdapter(context: Context) :
    RecyclerView.Adapter<ActorsListAdapter.ActorListViewHolder>() {

    private var actors = listOf<Actor>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorListViewHolder {
        return ActorListViewHolder(
            inflater.inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorListViewHolder, position: Int) {
        holder.onBind(actors[position])

    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
    }

    class ActorListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val actorImage: ImageView = itemView.findViewById(R.id.actor_ph)
        private val actorName: TextView = itemView.findViewById(R.id.actor_name)

        fun onBind(actor: Actor) {
            //actorImage.setImageResource(actor.image)
            actorName.text = actor.name
        }
    }

}