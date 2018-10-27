package com.ghozy19.footballapps.db

class FavoriteMatch(
        val id: Long?,
        val eventId: String?,
        val homeTeamName: String?,
        val homeTeamScore: String?,
        val awayTeamName: String?,
        val awayTeamScore: String?,
        val dateEvent: String?,
        val timeEvent: String?) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_TEAM_NAME: String = "HOME_NAME"
        const val HOME_TEAM_SCORE: String = "HOME_SCORE"
        const val AWAY_TEAM_NAME: String = "AWAY_NAME"
        const val AWAY_TEAM_SCORE: String = "AWAY_SCORE"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val TIME_EVENT: String = "TIME_EVENT"

    }
}