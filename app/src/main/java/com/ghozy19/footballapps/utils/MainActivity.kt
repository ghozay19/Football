package com.ghozy19.footballapps.utils



import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.fragment.ClubFragment
import com.ghozy19.footballapps.R.id.*
import com.ghozy19.footballapps.R.layout.activity_main
import com.ghozy19.footballapps.fragment.LastMatchFragment
import com.ghozy19.footballapps.fragment.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //TODO 1 buat navbottom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                nextMatch -> {
                    loadNextMatchFragment(savedInstanceState)
                }
                lastMatch -> {
                    loadLastMatchFragment(savedInstanceState)
                }
                clubs -> {
                    loadClubFragement(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = lastMatch
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.simpleName)
                    .commit()

        }
    }

    private fun loadLastMatchFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.simpleName)
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
