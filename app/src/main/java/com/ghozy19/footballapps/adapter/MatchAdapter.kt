package com.ghozy19.footballapps.adapter

import android.content.Context
import android.icu.text.TimeZoneFormat
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
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter(private val context: Context, private val match: List<EventsItem>, private val listener: (EventsItem) -> Unit)
    : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.match_item, parent, false))

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(match[position], listener)
    }

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

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



