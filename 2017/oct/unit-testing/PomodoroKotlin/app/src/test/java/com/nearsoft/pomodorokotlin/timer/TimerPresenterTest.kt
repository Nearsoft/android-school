package com.nearsoft.pomodorokotlin.timer

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class TimerPresenterTest {

    @Mock
    private lateinit var view: TimerContract.View

    private lateinit var timerPresenter: TimerPresenter

    @Before
    fun setupTest() {

        timerPresenter = TimerPresenter()
        timerPresenter.bindView(view)
    }
    
    @Test
    fun setTimeShouldResetTimer() {
        val presenter = spy(timerPresenter)
        presenter.setTime(1)
        verify(view).timerReset()
    }
    
    @Test
    fun setTimeShouldUpdateTheView() {
        timerPresenter.setTime(1)
        verify(view).updateTime(anyString())
    }

    @Test
    fun setTimeShouldUpdateTheViewWithTheCorrectFormat() {
        timerPresenter.setTime(TimeUnit.MINUTES.toMillis(2)+TimeUnit.SECONDS.toMillis(30))
        verify(view).updateTime("02:30")
    }

//    @Test
//    fun starTimerShouldLetTheViewKnow() {
//        timerPresenter.startTimer()
//        verify(view).timerStarted()
//    }

    @Test
    fun pauseTimerShouldLetTheViewKnow() {
        timerPresenter.pauseTimer()
        verify(view).timerPaused()
    }

    @Test
    fun resetTimerShouldLetTheViewKnow() {
        timerPresenter.resetTimer()
        verify(view).timerReset()
    }
}