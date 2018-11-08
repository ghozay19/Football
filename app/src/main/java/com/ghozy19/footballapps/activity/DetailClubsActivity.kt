package com.ghozy19.footballapps.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.adapter.ViewPagerAdapter
import com.ghozy19.footballapps.db.FavoriteClub
import com.ghozy19.footballapps.db.database
import com.ghozy19.footballapps.fragment.ClubDescFragment
import com.ghozy19.footballapps.fragment.PlayerFragment
import com.ghozy19.footballapps.model.club.Club
import kotlinx.android.synthetic.main.activity_detail_clubs.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailClubsActivity : AppCompatActivity() {

    private lateinit var club: Club

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_clubs)

        supportActionBar?.title = getString(R.string.detailClub)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        club = intent.getParcelableExtra("club")
        loadData()

        viewPagerSetup(viewpager_main)
        tabs_main.setupWithViewPager(viewpager_main)

        favoriteState()
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteClub.TABLE_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to club.teamId!!)
            val favorite = result.parseList(classParser<FavoriteClub>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }


    private fun loadData() {
        Glide.with(ctx).load(club.teamBadge).into(clubDetailLogo)
        clubDetailName.text = club.teamName
        clubDetailYears.text = club.teamFormedYear
        clubDetailStadium.text = club.teamStadium

        Log.d("nama stadium? ", "dapet?"+club.teamStadium)

    }

    private fun viewPagerSetup(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addExt(ClubDescFragment.newInstance(club), getString(R.string.detail))
        adapter.addExt(PlayerFragment.newInstance(club.teamId!!), getString(R.string.player))
        viewPager?.adapter = adapter
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
                insert(FavoriteClub.TABLE_FAVORITE,
                        FavoriteClub.TEAM_ID to club.teamId,
                        FavoriteClub.TEAM_NAME to club.teamName,
                        FavoriteClub.TEAM_BADGE to club.teamBadge,
                        FavoriteClub.TEAM_YEARS to club.teamFormedYear,
                        FavoriteClub.TEAM_STADIUM to club.teamStadium,
                        FavoriteClub.TEAM_DESC to club.teamDescription)
                Log.d("datanya masuk ? ", club.teamId)


            }
            toast(R.string.add_to_favorite)
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteClub.TABLE_FAVORITE, "(TEAM_ID = {id})",
                        "id" to club.teamId!!)
            }
            toast(R.string.removed_from_favorite)

        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }


    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
