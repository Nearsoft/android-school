package com.nearsoft.pomodorokotlin.timer

interface TimerContract {

    interface View {
        fun updateTime(text: String)

        fun timerStarted()

        fun timerPaused()

        fun timerCompleted()

        fun timerReset()
    }

    interface Presenter {
        fun startTimer()

        fun pauseTimer()

        fun resetTimer()

        fun setTime(milliseconds: Long)

        fun bindView(view: View)

        fun dropView()

        fun isTimerRunning() : Boolean

        fun getSelectedTime(): String
    }
}