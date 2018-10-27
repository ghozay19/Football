package com.ghozy19.footballapps.view.lastMatch

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.matchevent.ResponseEvent
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastMatchPresenter(
        private val view: LastMatchView,
        private val apiRepository: ApiRepository,
        private val gson: Gson
       ) {

    fun getLastMatch(league: String){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(
                    TheSportDBApi.getLeagueLastMatch(league)),
                    ResponseEvent::class.java)
            uiThread {
                view.hideLoading()
                view.showLastMatch(data.events)
                if (data.events?.size == null){
                    view.showNull()
                }

            }
        }
    }


}