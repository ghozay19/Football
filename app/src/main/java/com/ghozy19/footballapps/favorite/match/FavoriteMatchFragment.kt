package com.ghozy19.footballapps.favorite.match


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghozy19.footballapps.DetailMatchActivity
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.db.FavoriteMatch
import com.ghozy19.footballapps.db.database
import kotlinx.android.synthetic.main.fragment_favorite_match.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteMatchFragment : Fragment() {


    private var favMatch: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var adapter: MatchFavAdapter
    private lateinit var list: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_match, container, false)

        swipeRefreshLayout = view.swipeRefreshFavMatch




        adapter = MatchFavAdapter(ctx, favMatch) {
            ctx.startActivity<DetailMatchActivity>(
                    "idEvent" to it.eventId)
        }

        list = view.rvFavoriteMatch
        list.layoutManager = LinearLayoutManager(activity)
        list.adapter = adapter

        favMatch.clear()
        showFavoriteMatch()

        swipeRefreshLayout.onRefresh {
            favMatch.clear()
            showFavoriteMatch()
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)



        return view
    }

    private fun showFavoriteMatch() {

        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false

            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(parser = rowParser { id: Long?,
                                                                 eventId: String?,
                                                                 homeTeamName: String?,
                                                                 homeTeamScore: String?,
                                                                 awayTeamName: String?,
                                                                 awayTeamScore: String?,
                                                                 dateEvent: String?,
                                                                 timeEvent: String? ->
                FavoriteMatch(id, eventId, homeTeamName, homeTeamScore, awayTeamName, awayTeamScore, dateEvent, timeEvent)

            })

            favMatch.addAll(favorite)
            adapter.notifyDataSetChanged()


        }


    }
}
