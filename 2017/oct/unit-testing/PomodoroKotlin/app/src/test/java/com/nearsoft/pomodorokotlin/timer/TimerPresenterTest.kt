package com.nearsoft.pomodorokotlin.timer

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

class TimerPresenterTest {

    @Mock
    private lateinit var view: TimerContract.View

    private lateinit var timerPresenter: TimerPresenter

    @Before
    fun setupTest() {
        MockitoAnnotations.initMocks(this)
        timerPresenter = TimerPresenter()
        timerPresenter.bindView(view)
    }

    @Test
    fun setTimeShouldResetTimer() {
        TODO("Test not implemented")
    }
    
    @Test
    fun setTimeShouldUpdateTheView() {
        TODO("Test not implemented")
    }

    @Test
    fun setTimeShouldUpdateTheViewWithTheCorrectFormat() {
        TODO("Test not implemented")
    }

//    @Test
//    fun starTimerShouldLetTheViewKnow() {
//        timerPresenter.startTimer()
//        verify(view).timerStarted()
//    }

    @Test
    fun pauseTimerShouldLetTheViewKnow() {
        TODO("Test not implemented")
    }

    @Test
    fun resetTimerShouldLetTheViewKnow() {
        TODO("Test not implemented")
    }
}