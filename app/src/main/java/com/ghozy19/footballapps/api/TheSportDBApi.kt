package com.ghozy19.footballapps.api

import com.ghozy19.footballapps.BuildConfig

object TheSportDBApi {


    fun getClub(leagueId: String?):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookup_all_teams.php?id=" + leagueId
    }

    fun getPlayer(teamId: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookup_all_players.php?id=" + teamId
    }


    fun getDetailClub(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + teamId
    }

    fun getSearchEvent(query: String?):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php?e=" + query
    }
    fun getSearchClub(query: String?):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchteams.php?t=" + query
    }

    fun getLeagueNextMatch(idLeagueNext: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + idLeagueNext
    }

    fun getLeagueLastMatch(idLeagueLast: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + idLeagueLast

    }

        fun getMatchDetail(idEvent: String?) : String{
    return  BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +"/lookupevent.php?id=" + idEvent

    }

}