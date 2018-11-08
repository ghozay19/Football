package com.ghozy19.footballapps.view.lastMatch

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.matchevent.ResponseEvent
import com.ghozy19.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastMatchPresenter(
        private val view: LastMatchView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLastMatch(league: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(
                        TheSportDBApi.getLeagueLastMatch(league)),
                        ResponseEvent::class.java)
            }
            view.hideLoading()
            view.showLastMatch(data.await().events)
            if (data.await().events.size == null) {
                view.showNull()
            }

        }
    }
}
