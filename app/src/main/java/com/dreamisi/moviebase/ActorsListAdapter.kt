package com.dreamisi.moviebase

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        Log.d(TAG, "binding..............................")
        actors = newActors
    }

    class ActorListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val TAG = "ActorViewHolder"
        private val actorImage: ImageView = itemView.findViewById(R.id.actor_ph)
        private val actorName: TextView = itemView.findViewById(R.id.actor_name)

        fun onBind(actor: Actor) {
            Log.d(TAG, "OnBind..................")
            actorName.text = actor.name
            Log.d(TAG, "LoadImage..................")
            Glide.with(itemView).load(actor.imageUrl).into(actorImage)

        }
    }

    private val TAG = "ActorAdapter"

}