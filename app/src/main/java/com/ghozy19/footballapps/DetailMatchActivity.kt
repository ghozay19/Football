package com.ghozy19.footballapps

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.db.FavoriteMatch
import com.ghozy19.footballapps.db.database
import com.ghozy19.footballapps.model.matchevent.EventsItem
import com.ghozy19.footballapps.model.team.Club
import com.ghozy19.footballapps.utils.*
import com.ghozy19.footballapps.view.detailMatch.DetailMatchPresenter
import com.ghozy19.footballapps.view.detailMatch.DetailMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {


    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: DetailMatchPresenter

    private lateinit var idEvent: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var events: EventsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        supportActionBar?.title = getString(R.string.detailMatch)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        events = EventsItem()

        val intent = intent
        idEvent = intent.getStringExtra("idEvent")

        Log.d("apakah ada id eventnya?", idEvent)
        progressBar = progressBarDm

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson)
        presenter.getDetailMatch(idEvent)

        favoriteState()

    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showNull() {
        toast("No data available")
    }


    override fun showDetailClub(data1: List<Club>?, data2: List<Club>?) {

        tvStadium.text = data1?.get(0)?.teamStadium

        //HomeTeam
        tvClubHome.text = data1?.get(0)?.teamName
        Glide.with(this)
                .load(data1?.get(0)?.teamBadge)
                .into(ivClubHome)


        //AwayTEam
        tvClubAway.text = data2?.get(0)?.teamName
        Glide.with(this)
                .load(data2?.get(0)?.teamBadge)
                .into(ivClubAway)
    }


    override fun showDetailMatch(data: List<EventsItem>?) {


        events.idEvent = data?.get(index = 0)?.idEvent
        events.strHomeTeam = data?.get(index = 0)?.strHomeTeam
        events.strAwayTeam = data?.get(index = 0)?.strAwayTeam
        events.intHomeScore = data?.get(index = 0)?.intHomeScore
        events.intAwayScore = data?.get(index = 0)?.intAwayScore


        val idHomeTeam = data?.get(index = 0)?.idHomeTeam
        val idAwayTeam = data?.get(index = 0)?.idAwayTeam

        presenter.getDetailClub(idHomeTeam, idAwayTeam)

        if (data?.get(index = 0)?.intHomeScore != null) {

            tvNameLeague.text = data.get(index = 0).strLeague
            tvGameWeek.text = getString(R.string.game_week) + data.get(index = 0).intRound

            val date = strToDate(data.get(index = 0).dateEvent)
            val dateTime = toGMTFormat(data.get(index = 0).dateEvent, data.get(index = 0).strTime)
            val timeNew = SimpleDateFormat("HH:mm").format(dateTime)
            val dateNew = changeFormatDate(date)

            tvDateEventDetail.text = dateNew
            tvTimeEventDetail.text = timeNew


//HomeTeam
            tvScoreHome.text = data.get(index = 0).intHomeScore
            tvHomeGoalScorer.text = data.get(index = 0).strHomeGoalDetails?.replace(";", "\n")
            tvHomeGK.text = data.get(index = 0).strHomeLineupGoalkeeper?.replace(";", "\n")
            tvHomeDF.text = data.get(index = 0).strHomeLineupDefense?.replace(";", "\n")
            tvHomeMF.text = data.get(index = 0).strHomeLineupMidfield?.replace(";", "\n")
            tvHomeFW.text = data.get(index = 0).strHomeLineupForward?.replace(";", "\n")
            tvHomeSubs.text = data.get(index = 0).strHomeLineupSubstitutes?.replace(";", "\n")
            tvHomeYellow.text = data.get(index = 0).strHomeYellowCards?.replace(";", "\n")
            tvHomeRed.text = data.get(index = 0).strHomeRedCards?.replace(";", "\n")
            tvHomeOnTarget.text = data.get(index = 0).intHomeShots as CharSequence?

//AwayTeam
            tvScoreAway.text = data.get(index = 0).intAwayScore
            tvAwayGoalScorer.text = data.get(index = 0).strAwayGoalDetails?.replace(";", "\n")
            tvAwayGK.text = data.get(index = 0).strAwayLineupGoalkeeper?.replace(";", "\n")
            tvAwayDF.text = data.get(index = 0).strAwayLineupDefense?.replace(";", "\n")
            tvAwayMF.text = data.get(index = 0).strAwayLineupMidfield?.replace(";", "\n")
            tvAwayFW.text = data.get(index = 0).strAwayLineupForward?.replace(";", "\n")
            tvAwaySubs.text = data.get(index = 0).strAwayLineupSubstitutes?.replace(";", "\n")
            tvAwayYellow.text = data.get(index = 0).strAwayYellowCards?.replace(";", "\n")
            tvAwayRed.text = data.get(index = 0).strAwayRedCards?.replace(";", "\n")
            tvAwayOnTarget.text = data.get(index = 0).intAwayShots as CharSequence?
        } else {
            tvNameLeague.text = data?.get(index = 0)?.strLeague.toString()

            tvGameWeek.text = getString(R.string.game_week) + data?.get(index = 0)?.intRound


            val date = strToDate(data?.get(index = 0)?.dateEvent)
            val dateTime = toGMTFormat(data?.get(index = 0)?.dateEvent, data?.get(index = 0)?.strTime)
            val timeNew = SimpleDateFormat("HH:mm").format(dateTime)
            val dateNew = changeFormatDate(date)

            tvDateEventDetail.text = dateNew
            tvTimeEventDetail.text = timeNew


        }


    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs("(EVENT_ID = {idEvent})",
                            "idEvent" to idEvent)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {

        try {
            database.use {



                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.EVENT_ID to events.idEvent.toString(),
                        FavoriteMatch.HOME_TEAM_NAME to events.strHomeTeam.toString(),
                        FavoriteMatch.HOME_TEAM_SCORE to events.intHomeScore.toString(),
                        FavoriteMatch.AWAY_TEAM_NAME to events.strAwayTeam.toString(),
                        FavoriteMatch.AWAY_TEAM_SCORE to events.intAwayScore.toString(),
                        FavoriteMatch.DATE_EVENT to tvDateEventDetail.text.toString(),
                        FavoriteMatch.TIME_EVENT to tvTimeEventDetail.text.toString())

                Log.i("db", "mashook pak Eko ! :" + events.strHomeTeam)
            }
            snackbar(progressBar, getString(R.string.add_to_favorite)).show()
        } catch (e: SQLiteConstraintException) {
            snackbar(progressBar, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(EVENT_ID = {idEvent})",
                        "idEvent" to idEvent)
            }
            snackbar(progressBar, getString(R.string.removed_from_favorite)).show()

        } catch (e: SQLiteConstraintException) {
            snackbar(progressBar, e.localizedMessage).show()
        }
    }


    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }


}

