package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), JokesFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, JokesFragment.newInstance(), "list")
                .commit()
        }
    }

    override fun onJokeClick(joke: Joke) {
        val detail = JokesFragmentAdapter.newInstance(joke)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, detail, "detail")
            .addToBackStack(null)
            .commit()
    }
}
