package com.nearsoft.pomodorokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.nearsoft.pomodorokotlin.timer.TimerContract
import com.nearsoft.pomodorokotlin.timer.TimerFragment
import com.nearsoft.pomodorokotlin.timer.TimerPresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var timerPresenter: TimerContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        timerPresenter = TimerPresenter()
        timerPresenter.setTime(TimeUnit.MINUTES.toMillis(5))

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment) as TimerFragment
        fragment.setPresenter(timerPresenter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item?.itemId

        return if (id == R.id.action_settings) true else super.onOptionsItemSelected(item)
    }
}
