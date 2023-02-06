package com.dreamisi.moviebase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.dreamisi.moviebase.data.ActorsDataSource

class MoviesDetailsFragment : Fragment(R.layout.fragment_movies_details) {

    private var recycler: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.actor_cards)
        recycler?.adapter = ActorsListAdapter(requireContext())
        recycler?.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? ActorsListAdapter)?.apply {
            bindActors(ActorsDataSource().getActors())
        }
    }
}