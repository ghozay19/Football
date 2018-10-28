package com.ghozy19.footballapps.utils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.R.id.*
import com.ghozy19.footballapps.R.layout.activity_main
import com.ghozy19.footballapps.favorite.FavoriteFragment
import com.ghozy19.footballapps.fragment.ClubFragment
import com.ghozy19.footballapps.fragment.match.MatchFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                match -> {
                    loadMatchFragment(savedInstanceState)
                }
                favorite -> {
                    loadFavoriteFragment(savedInstanceState)
                }
                clubs -> {
                    loadClubFragement(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = match
    }


    private fun loadMatchFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchFragment::class.simpleName)
                    .commit()

        }
    }


    private fun loadFavoriteFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadClubFragement(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, ClubFragment(), ClubFragment::class.simpleName)
                .commit()
    }
    }

}
