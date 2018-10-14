package com.ghozy19.footballapps.view.NextMatch

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.matchevent.ResponseEvent
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(
        private val view: NextMatchView,
        private val apiRepository: ApiRepository,
        private val gson: Gson
){

    fun getNextMatch(league: String){
        view.showLaoding()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi
                    .getLeagueNextMatch(league)),
                    ResponseEvent::class.java)

            uiThread {
                view.hideLoading()
                view.showNextMatch(data.events)
                if (data.events?.size == null){
                            view.showNull()
                        }


            }
        }
    }
}