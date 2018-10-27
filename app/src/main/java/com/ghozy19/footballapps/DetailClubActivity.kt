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
import com.ghozy19.footballapps.R.drawable.ic_add_to_favorites
import com.ghozy19.footballapps.R.drawable.ic_added_to_favorites
import com.ghozy19.footballapps.R.id.add_to_favorite
import com.ghozy19.footballapps.R.menu.detail_menu
import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.db.FavoriteClub
import com.ghozy19.footballapps.db.database
import com.ghozy19.footballapps.model.team.Club
import com.ghozy19.footballapps.utils.invisible
import com.ghozy19.footballapps.utils.visible
import com.ghozy19.footballapps.view.detailClub.DetailClubPresenter
import com.ghozy19.footballapps.view.detailClub.DetailClubView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_club.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailClubActivity : AppCompatActivity(), DetailClubView {

    private lateinit var clubs: Club
    private lateinit var presenter: DetailClubPresenter
    private lateinit var progressBar: ProgressBar

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false


    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_club)

        progressBar = progressBarDc

        supportActionBar?.title = getString(R.string.detailClub)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        id = intent.getStringExtra("id")
        Log.d("dapet idnya ga ?", id)

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailClubPresenter(this, request, gson)
        presenter.getDetailClub(id)

        favoriteState()

    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteClub.TABLE_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<FavoriteClub>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showDetailClub(data: List<Club>) {
        clubs = Club(data[0].teamId,
                data[0].teamName,
                data[0].teamBadge)


        Glide.with(this)
                .load(data.get(index = 0).teamBadge)
                .into(clubLogoDetail)

        clubStadiumDetail.text = data.get(index = 0).teamStadium + getString(R.string.stadium)
        clubYearsDetail.text = data.get(index = 0).teamFormedYear
        clubDescDetail.text = data.get(index = 0).teamDescription
        clubNameDetail.text = data.get(index = 0).teamName

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
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
            add_to_favorite -> {
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
                insert(FavoriteClub.TABLE_FAVORITE,
                        FavoriteClub.TEAM_ID to clubs.teamId,
                        FavoriteClub.TEAM_NAME to clubs.teamName,
                        FavoriteClub.TEAM_BADGE to clubs.teamBadge)
                Log.d("datanya masuk ? ", clubs.teamId)


            }
            snackbar(progressBar, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(progressBar, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteClub.TABLE_FAVORITE, "(TEAM_ID = {id})",
                        "id" to id)
            }
            snackbar(progressBar, "Removed to favorite").show()

        } catch (e: SQLiteConstraintException) {
            snackbar(progressBar, e.localizedMessage).show()
        }
    }


    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}
