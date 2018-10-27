package com.ghozy19.footballapps.view.nextMatch

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.matchevent.ResponseEvent
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(
        private val view: NextMatchView,
        private val apiRepository: ApiRepository,
        private val gson: Gson
) {

    fun getNextMatch(league: String) {
        view.showLaoding()
        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi
                        .getLeagueNextMatch(league)),
                        ResponseEvent::class.java)

            }
            view.hideLoading()
            view.showNextMatch(data.await().events)
            if (data.await().events?.size == null) {
                view.showNull()
            }


        }
    }
}
