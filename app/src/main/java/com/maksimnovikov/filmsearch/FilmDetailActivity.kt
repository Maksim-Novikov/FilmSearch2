package com.maksimnovikov.filmsearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FilmDetailActivity : BaseActivity() {

    companion object {

        const val FILM_DETAIL_ARGUMENT_KEY = "FILM_DETAIL_ARGUMENT_KEY"
        const val FILM_DETAIL_RESULT_KEY = "FILM_DETAIL_RESULT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        val film = intent.getParcelableExtra<Film>(FILM_DETAIL_ARGUMENT_KEY)

        findViewById<TextView>(R.id.film_detail_name)?.text = film?.name

        findViewById<Button>(R.id.film_detail_back)?.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra(FILM_DETAIL_RESULT_KEY, 10))
            finish()
        }
    }
}