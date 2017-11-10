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
    private lateinit var timerText: TextView
    private lateinit var breakSmallOption: RadioButton
    private lateinit var breakLargeOption: RadioButton
    private lateinit var workOption: RadioButton
    private lateinit var fab: FloatingActionButton
    private lateinit var presenter: TimerPresenter

    @Before
    fun setup() {
        presenter = TimerPresenter()
        val fragment = TimerFragment()
        SupportFragmentTestUtil.startVisibleFragment(fragment)
        fragment.setPresenter(presenter)

        val view = fragment.view!!

        timerText = view.findViewById(R.id.timerTextView)
        breakSmallOption = view.findViewById(R.id.breakSmallRadioButton)
        breakLargeOption = view.findViewById(R.id.breakLargeRadioButton)
        workOption = view.findViewById(R.id.workRadioButton)
        fab = view.findViewById(R.id.fab)
    }

    @Test
    fun timerTextShouldHaveAnInitialValue() {
        assertFalse(StringUtils.isEmpty(timerText.text.toString()))
    }

    @Test
    fun selectingPomodoroShouldChangeText() {
        breakLargeOption.performClick()
        assertEquals("05:00", timerText.text)

        breakSmallOption.performClick()
        assertEquals("01:00", timerText.text)

        workOption.performClick()
        assertEquals("05:00", timerText.text)
    }

    @Test
    fun startingPomodoroShouldChangeFabIcon() {
        var drawable = shadowOf(fab.drawable)
        Assert.assertEquals(android.R.drawable.ic_media_play, drawable.createdFromResId)
        fab.performClick()
        drawable = shadowOf(fab.drawable)
        Assert.assertEquals(android.R.drawable.ic_media_pause, drawable.createdFromResId)
    }

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