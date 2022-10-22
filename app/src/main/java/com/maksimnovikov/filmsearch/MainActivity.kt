package com.maksimnovikov.filmsearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : BaseActivity() {

    companion object {

        private const val COUNTER_KEY = "COUNTER_KEY"
        private const val FILM_DETAIL_REQUEST_CODE = 10
    }

    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button? = findViewById(R.id.mainCounterAdd)
        button?.setOnClickListener {
            add()
        }
        findViewById<Button>(R.id.mainDetail)?.setOnClickListener {
            openDetail()
        }
    }

    private fun openDetail() {
        val film = Film(
            "Властелин колец",
            2003
        )
        startActivityForResult(
            Intent(this, FilmDetailActivity::class.java)
                .apply {
                    putExtra(FilmDetailActivity.FILM_DETAIL_ARGUMENT_KEY, film)
                },
            FILM_DETAIL_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FILM_DETAIL_REQUEST_CODE) {
            val dataValue = data?.getIntExtra(FilmDetailActivity.FILM_DETAIL_RESULT_KEY, 0)
            if (dataValue != null) {
                showResult(dataValue)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun showResult(result: Int) {
        Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COUNTER_KEY, counter)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        counter = savedInstanceState.getInt(COUNTER_KEY, 0)
        updateCounter()
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun add() {
        counter += 1
        updateCounter()
    }

    private fun updateCounter() {
        findViewById<TextView>(R.id.mainCounterText)?.apply {
            text = counter.toString()
        }
    }
}