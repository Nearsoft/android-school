package com.nearsoft.pomodorokotlin.timer

import android.os.CountDownTimer
import java.util.*
import java.util.concurrent.TimeUnit

open class TimerPresenter : TimerContract.Presenter {

    var view: TimerContract.View? = null
    private val timeFormat = "%02d:%02d"
    private var countDownTimer: CountDownTimer? = null
    private var selectedTime: Long = 0
    private var remainingTime: Long = 0
    private var shouldPauseTimer: Boolean = false

    override fun bindView(view: TimerContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

    override fun setTime(milliseconds: Long) {
        resetTimer()
        selectedTime = milliseconds
        view?.updateTime(timeToString(milliseconds))
    }

    override fun startTimer() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
            countDownTimer = null
        }

        val time: Long
        if (remainingTime > 0)
        {
            time = remainingTime
            remainingTime = 0
        } else {
            time = selectedTime
        }

        countDownTimer = createNewTimer(time)
        countDownTimer!!.start()
        view?.timerStarted()
    }

    override fun pauseTimer() {
        shouldPauseTimer = true
        view?.timerPaused()
    }

    override fun isTimerRunning(): Boolean {
        return countDownTimer != null && remainingTime == 0L
    }

    override fun resetTimer() {
        // Reset everything
        countDownTimer?.cancel()
        countDownTimer = null
        remainingTime = 0
        view?.timerReset()
    }

    override fun getSelectedTime(): String {
        return timeToString(selectedTime)
    }

    private fun createNewTimer(milliseconds: Long): CountDownTimer {
        return object : CountDownTimer(milliseconds, 500) {
            override fun onTick(l: Long) {
                if(shouldPauseTimer) {
                    shouldPauseTimer = false
                    remainingTime = l
                    this.cancel()
                }

                view?.updateTime(timeToString(l))
            }

            override fun onFinish() {
                // Timer completed
                view?.timerCompleted()
                countDownTimer = null
            }
        }
    }

    private fun timeToString(milliseconds: Long): String {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(minutes)
        return String.format(Locale.getDefault(),
                timeFormat, minutes, seconds)
    }
}