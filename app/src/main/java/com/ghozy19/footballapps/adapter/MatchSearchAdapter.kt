package com.ghozy19.footballapps.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.model.matchevent.EventsItem
import com.ghozy19.footballapps.utils.changeFormatDate
import com.ghozy19.footballapps.utils.strToDate
import com.ghozy19.footballapps.utils.toGMTFormat
import kotlinx.android.synthetic.main.match_item.view.*
import java.text.SimpleDateFormat

class MatchSearchAdapter(private val context: Context, private val match: List<EventsItem>, private val listener: (EventsItem) -> Unit)
    : RecyclerView.Adapter<MatchSearchAdapter.MatchSearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MatchSearchViewHolder(LayoutInflater.from(context).inflate(R.layout.match_item, parent, false))

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: MatchSearchViewHolder, position: Int) {
        holder.bindItem(match[position], listener)
    }

    class MatchSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(match: EventsItem, listener: (EventsItem) -> Unit) {

            itemView.clubHome.text = match.strHomeTeam
            itemView.clubAway.text = match.strAwayTeam

            itemView.idEventMatch.text = match.idEvent


            if (match.intHomeScore == null) {
                itemView.score.text = " VS "
            } else {
                itemView.score.text = match.intHomeScore + " : " + match.intAwayScore
            }
            itemView.setOnClickListener { listener(match) }


            val date = strToDate(match.dateEvent)
            val dateTime = toGMTFormat(match.dateEvent, match.strTime)
            itemView.timeMatch.text = SimpleDateFormat("HH:mm").format(dateTime)
            itemView.dateMatch.text = changeFormatDate(date)


        }

    }
}



