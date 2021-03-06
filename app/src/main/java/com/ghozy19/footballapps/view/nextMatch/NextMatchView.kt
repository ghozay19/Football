package com.ghozy19.footballapps.view.nextMatch

import com.ghozy19.footballapps.model.matchevent.EventsItem

interface NextMatchView {

    fun showLaoding()
    fun hideLoading()
    fun showNextMatch(data: List<EventsItem>?)
    fun showNull()
}