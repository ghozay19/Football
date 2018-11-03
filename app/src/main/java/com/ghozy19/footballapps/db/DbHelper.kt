package com.ghozy19.footballapps.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "db_favorite.db", null, 1) {
    companion object {
        private var instance: DbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DbHelper {
            if (instance == null) {
                instance = DbHelper(ctx.applicationContext)
            }
            return instance as DbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
                FavoriteClub.TABLE_FAVORITE, true,
                FavoriteClub.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteClub.TEAM_ID to TEXT + UNIQUE,
                FavoriteClub.TEAM_NAME to TEXT,
                FavoriteClub.TEAM_BADGE to TEXT,
                FavoriteClub.TEAM_YEARS to TEXT,
                FavoriteClub.TEAM_STADIUM to TEXT,
                FavoriteClub.TEAM_DESC to TEXT
        )

        db?.createTable(
                FavoriteMatch.TABLE_FAVORITE_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.EVENT_ID to TEXT + UNIQUE,
                FavoriteMatch.HOME_TEAM_NAME to TEXT,
                FavoriteMatch.HOME_TEAM_SCORE to TEXT,
                FavoriteMatch.AWAY_TEAM_NAME to TEXT,
                FavoriteMatch.AWAY_TEAM_SCORE to TEXT,
                FavoriteMatch.DATE_EVENT to TEXT,
                FavoriteMatch.TIME_EVENT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteClub.TABLE_FAVORITE, true)
        db?.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
    }
}

val Context.database: DbHelper
    get() = DbHelper.getInstance(applicationContext)

