package com.dreamisi.moviebase.moviedetails

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dreamisi.moviebase.R
import com.dreamisi.moviebase.data.models.Actor

class ActorsListAdapter(context: Context) :
    ListAdapter<Actor, ActorsListAdapter.ActorListViewHolder>(ActorsCallback()) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorListViewHolder {
        return ActorListViewHolder(
            inflater.inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorListViewHolder, position: Int) {
        holder.onBind(getItem(position))

    }

    class ActorListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val actorImage: ImageView = itemView.findViewById(R.id.actor_ph)
        private val actorName: TextView = itemView.findViewById(R.id.actor_name)

        fun onBind(actor: Actor) {
            Log.d(TAG, "OnBind..................")
            actorName.text = actor.name
            Log.d(TAG, "LoadImage..................")
            Glide.with(itemView).load(actor.imageUrl).into(actorImage)

        }

        companion object {
            private const val TAG = "ActorViewHolder"

        }
    }

    class ActorsCallback : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean =
            oldItem == newItem

    }
}