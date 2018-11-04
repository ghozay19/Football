package com.ghozy19.footballapps.view.detailMatch

import com.ghozy19.footballapps.model.club.Club
import com.ghozy19.footballapps.model.matchevent.EventsItem

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNull()
    fun showDetailMatch(data: List<EventsItem>?)
    fun showDetailClub(data1: List<Club>?, data2: List<Club>?)

}