package com.ghozy19.footballapps.api

import com.ghozy19.footballapps.BuildConfig

object TheSportDBApi {

    //TODO jadi catatan reviewer disuruh search by id saja
    fun getClub(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + league
    }


    fun getDetailClub(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + teamId
    }


    fun getLeagueNextMatch(idLeagueNext: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + idLeagueNext
    }


    //    https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328
    fun getLeagueLastMatch(idLeagueLast: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + idLeagueLast

    }


////        https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=441613
        fun getMatchDetail(idEvent: String?) : String{
    return  BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +"/lookupevent.php?id=" + idEvent

    }

}