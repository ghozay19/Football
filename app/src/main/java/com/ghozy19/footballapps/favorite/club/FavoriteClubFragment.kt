package com.ghozy19.footballapps.favorite.club


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghozy19.footballapps.DetailClubsActivity
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.db.FavoriteClub
import com.ghozy19.footballapps.db.database
import com.ghozy19.footballapps.model.club.Club
import kotlinx.android.synthetic.main.fragment_favorite_club.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh


class FavoriteClubFragment : Fragment() {


    private var favClub: MutableList<FavoriteClub> = mutableListOf()
    private lateinit var adapter: ClubFavAdapter
    private lateinit var list: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_club, container, false)

        swipeRefreshLayout = view.swipeRefreshFav

        adapter = ClubFavAdapter(ctx, favClub) {
            val club = Club(
                    it.teamId,
                    it.teamName,
                    it.teamBadge,
                    it.teamYears,
                    it.teamStadium,
                    it.teamDesc
            )
            ctx.startActivity<DetailClubsActivity>("club" to club)
        }


        list = view.rvFavorite
        list.layoutManager = LinearLayoutManager(activity)
        list.adapter = adapter

        favClub.clear()
        showFavoriteClub()

        swipeRefreshLayout.onRefresh {
            favClub.clear()
            showFavoriteClub()
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)


        return view
    }


    private fun showFavoriteClub() {
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(FavoriteClub.TABLE_FAVORITE)
            val favoriteClub = result.parseList(classParser<FavoriteClub>())
            favClub.addAll(favoriteClub)
            adapter.notifyDataSetChanged()
        }
    }

}
