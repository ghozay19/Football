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
import com.ghozy19.footballapps.DetailClubActivity
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.db.Favorite
import com.ghozy19.footballapps.db.database
import kotlinx.android.synthetic.main.fragment_favorite_club.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh


class FavoriteClubFragment : Fragment() {
//TODO cek dan ketik ulang


    private var favClub: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: ClubFavAdapter
    private lateinit var list: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

//    private var id: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_club, container, false)

        swipeRefreshLayout = view.swipeRefreshFav




        adapter = ClubFavAdapter(ctx, favClub) {
            ctx.startActivity<DetailClubActivity>(
                    "id" to it.teamId)
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


        return view;
    }


    private fun showFavoriteClub(){
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favoriteClub = result.parseList(classParser<Favorite>())
            favClub.addAll(favoriteClub)
            adapter.notifyDataSetChanged()
        }
    }

}
