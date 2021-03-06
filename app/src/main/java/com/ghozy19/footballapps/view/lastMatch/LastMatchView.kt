package com.ghozy19.footballapps.view.lastMatch

import com.ghozy19.footballapps.model.matchevent.EventsItem

interface LastMatchView {

    fun showLoading()
    fun hideLoading()
    fun showNull()
    fun showLastMatch(data: List<EventsItem>?)
}