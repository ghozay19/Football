package com.ghozy19.footballapps.view.searchMatch

import com.ghozy19.footballapps.model.matchevent.EventsItem

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showEventsList(data: List<EventsItem>)
}