package com.reisdeveloper.githubapiconsumer

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.reisdeveloper.githubapiconsumer.ui.MainActivity
import com.reisdeveloper.githubapiconsumer.ui.home.HomeFragment
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private val STRING_TO_BE_TYPED = "Espresso"

    @JvmField
    @Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun changeText_sameActivity() {
        launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.imgUser)).perform(click())
    }
}