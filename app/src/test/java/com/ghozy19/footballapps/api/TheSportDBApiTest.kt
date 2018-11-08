package com.ghozy19.footballapps.api

import org.junit.Test

import org.junit.Assert.*

class TheSportDBApiTest {


    @Test
    fun getClub() {

        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        assertEquals(url, TheSportDBApi.getClub("English%20Premier%20League"))
    }

    @Test
    fun getDetailClub() {
        val url = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=133604"
        assertEquals(url, TheSportDBApi.getDetailClub("133604"))
    }

    @Test
    fun getLeagueNextMatch() {
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4331"
        assertEquals(url, TheSportDBApi.getLeagueNextMatch("4331"))
    }

    @Test
    fun getLeagueLastMatch() {
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4331"
        assertEquals(url, TheSportDBApi.getLeagueLastMatch("4331"))
    }

    @Test
    fun getMatchDetail() {

        val url = "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=576561"
        assertEquals(url, TheSportDBApi.getMatchDetail("576561"))
    }

    @Test
    fun getSearchClub(){
        val url ="https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=arsenal"
        assertEquals(url, TheSportDBApi.getSearchClub("arsenal"))

    }
}