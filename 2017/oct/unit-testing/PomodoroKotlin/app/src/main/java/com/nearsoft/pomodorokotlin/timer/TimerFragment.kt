package com.nearsoft.pomodorokotlin.timer


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nearsoft.pomodorokotlin.R
import kotlinx.android.synthetic.main.fragment_timer.*
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 */
class TimerFragment : Fragment(), TimerContract.View {

    private lateinit var presenter: TimerContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            var minutes = 0

            when (checkedId) {
                R.id.workRadioButton -> minutes = 5
                R.id.breakLargeRadioButton -> minutes = 5
                R.id.breakSmallRadioButton -> minutes = 1
            }

            presenter.setTime(TimeUnit.MINUTES.toMillis(minutes.toLong()))
            resetButton.visibility = View.INVISIBLE
        }

        fab.setOnClickListener {
            if(presenter.isTimerRunning()) {
                presenter.pauseTimer()
            } else {
                presenter.startTimer()
            }
        }

        resetButton.setOnClickListener {
            presenter.resetTimer()
            resetButton.visibility = View.INVISIBLE
        }
    }

    fun setPresenter(presenter: TimerContract.Presenter) {
        this.presenter = presenter
        this.presenter.bindView(this)
        timerTextView.text = presenter.getSelectedTime()
    }

    override fun updateTime(text: String) {
        timerTextView.text = text
    }

    override fun timerStarted() {
        // Show pause button
        fab.setImageResource(android.R.drawable.ic_media_pause)
        // Show stop button
        resetButton.visibility = View.VISIBLE
    }

    override fun timerPaused() {
        // Show play button
        fab.setImageResource(android.R.drawable.ic_media_play)
    }

    override fun timerCompleted() {
        timerTextView.text = getString(R.string.timer_completed_text)
        fab.setImageResource(android.R.drawable.ic_media_play)
    }

    override fun timerReset() {
        // Show play button
        fab.setImageResource(android.R.drawable.ic_media_play)
        timerTextView.text = presenter.getSelectedTime()
    }


}// Required empty public constructor
