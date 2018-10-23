package com.ghozy19.footballapps.db

//TODO step kedua setelah insert library ank sqlite
class Favorite(
        val id: Long?,
        val teamId: String?,
        val teamName: String?,
        val teamBadge: String?) {

    companion object {
//TODO Fav Club cukup id name badge aja
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"

    }
}