package com.ghozy19.footballapps.view.DetailMatch

import com.ghozy19.footballapps.model.matchevent.EventsItem
import com.ghozy19.footballapps.model.team.Club

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNull()
    fun showDetailMatch(data: List<EventsItem>?)
    fun showDetailClub(data1: List<Club>?, data2: List<Club>?)

}