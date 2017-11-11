package com.nearsoft.pomodorokotlin.timer

import android.support.design.widget.FloatingActionButton
import android.widget.RadioButton
import android.widget.TextView
import com.nearsoft.pomodorokotlin.R
import org.codehaus.plexus.util.StringUtils
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil

@RunWith(RobolectricTestRunner::class)
class TimerFragmentTest {
    private lateinit var fab: FloatingActionButton
    private lateinit var presenter: TimerPresenter

    @Before
    fun setup() {
        presenter = TimerPresenter()
        val fragment = TimerFragment()

        fragment.setPresenter(presenter)

        val view = fragment.view!!

        fab = view.findViewById(R.id.fab)
    }

    // TEST - timerTextShouldHaveAnInitialValue

    // TEST - selectingPomodoroShouldChangeText

    @Test
    fun fabShouldHaveCorrectImageDependingOnState() {
        // Should begin with play icon
        var drawable = shadowOf(fab.drawable)
        Assert.assertEquals(android.R.drawable.ic_media_play, drawable.createdFromResId)

        presenter.startTimer()
        drawable = shadowOf(fab.drawable)
        Assert.assertEquals(android.R.drawable.ic_media_pause, drawable.createdFromResId)

        presenter.pauseTimer()
        drawable = shadowOf(fab.drawable)
        Assert.assertEquals(android.R.drawable.ic_media_play, drawable.createdFromResId)

        presenter.startTimer()
        drawable = shadowOf(fab.drawable)
        Assert.assertEquals(android.R.drawable.ic_media_pause, drawable.createdFromResId)

        presenter.resetTimer()
        drawable = shadowOf(fab.drawable)
        Assert.assertEquals(android.R.drawable.ic_media_play, drawable.createdFromResId)
    }

}