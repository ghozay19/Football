package com.ghozy19.footballapps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.model.matchevent.EventsItem
import com.ghozy19.footballapps.model.team.Club
import com.ghozy19.footballapps.utils.invisible
import com.ghozy19.footballapps.utils.visible
import com.ghozy19.footballapps.view.DetailMatch.DetailMatchPresenter
import com.ghozy19.footballapps.view.DetailMatch.DetailMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.toast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {


    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: DetailMatchPresenter

    private  var idEvent: String =""
    private lateinit var idHome: String
    private lateinit var idAway: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        val intent = intent
        idEvent = intent.getStringExtra("idEvent")
        idHome = intent.getStringExtra("idHome")
        idAway = intent.getStringExtra("idAway")
        idEventMatch.text = idEvent
        Log.d("apakah ada id eventnya?", idEvent)
        progressBar = progressBarDm

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson)
        presenter.getDetailMatch(idEvent)
        presenter.getDetailClub(idHome, idAway)


    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showNull() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun showDetailClub(data1: List<Club>?, data2: List<Club>?) {

        tvStadium.text = data1?.get(0)?.teamStadium + " Stadium"

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

        if (data?.get(0) != null) {

            tvNameLeague.text = data?.get(0)?.strLeague
            tvGameWeek.text = "Game Week " + data?.get(0)?.intRound

            var dateEventNew = data?.get(0)?.dateEvent


            var locale = Locale("ID")
            var date_format = SimpleDateFormat("yyyy-MM-dd", locale)
            val date: Date
            try {
                date = date_format.parse(dateEventNew)
                date_format = SimpleDateFormat("EEEE, dd MMMM yyyy", locale)
                dateEventNew = date_format.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            tvDateEventDetail.text = dateEventNew


//HomeTeam
            tvScoreHome.text = data?.get(0)?.intHomeScore
            tvHomeGoalScorer.text = data?.get(0)?.strHomeGoalDetails?.replace(";", "\n")
            tvHomeGK.text = data?.get(0)?.strHomeLineupGoalkeeper?.replace(";", "\n")
            tvHomeDF.text = data?.get(0)?.strHomeLineupDefense?.replace(";", "\n")
            tvHomeMF.text = data?.get(0)?.strHomeLineupMidfield?.replace(";", "\n")
            tvHomeFW.text = data?.get(0)?.strHomeLineupForward?.replace(";", "\n")
            tvHomeSubs.text = data?.get(0)?.strHomeLineupSubstitutes?.replace(";", "\n")
            tvHomeYellow.text = data?.get(0)?.strHomeYellowCards?.replace(";", "\n")
            tvHomeRed.text = data?.get(0)?.strHomeRedCards?.replace(";", "\n")
            tvHomeOnTarget.text = data?.get(0)?.intHomeShots as CharSequence?

//AwayTeam
            tvScoreAway.text = data?.get(0)?.intAwayScore
            tvAwayGoalScorer.text = data?.get(0)?.strAwayGoalDetails?.replace(";", "\n")
            tvAwayGK.text = data?.get(0)?.strAwayLineupGoalkeeper?.replace(";", "\n")
            tvAwayDF.text = data?.get(0)?.strAwayLineupDefense?.replace(";", "\n")
            tvAwayMF.text = data?.get(0)?.strAwayLineupMidfield?.replace(";", "\n")
            tvAwayFW.text = data?.get(0)?.strAwayLineupForward?.replace(";", "\n")
            tvAwaySubs.text = data?.get(0)?.strAwayLineupSubstitutes?.replace(";", "\n")
            tvAwayYellow.text = data?.get(0)?.strAwayYellowCards?.replace(";", "\n")
            tvAwayRed.text = data?.get(0)?.strAwayRedCards?.replace(";", "\n")
            tvAwayOnTarget.text = data?.get(0)?.intAwayShots as CharSequence?
        } else {
            toast("False")
        }


    }
}

