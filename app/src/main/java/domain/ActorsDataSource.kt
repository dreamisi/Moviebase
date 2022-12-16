package domain

import com.dreamisi.moviebase.R
import data.models.Actor

class ActorsDataSource {

    fun getActors(): List<Actor> {
        return listOf(
            Actor("Robert Downey Jr.", R.drawable.robert_downey_jr),
            Actor("Chris Evans", R.drawable.chris_evans),
            Actor("Mark Ruffalo", R.drawable.mark_ruffalo),
            Actor("Chris Hemsworth", R.drawable.chris_hemsworth)

        )
    }
}