package com.llm

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.llm.ui.DeliveryListActivity
import com.llm.ui.adapter.DeliveryItemViewHolder
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DeliveryListActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<DeliveryListActivity> = ActivityTestRule(DeliveryListActivity::class.java)


    @Before
    open fun init() {
        Intents.init()
    }

    @After
    fun teardown() {
        Intents.release()
    }


    @Test
    fun testSwipeToRefresh(){
        onView(withId(R.id.swipe_refresh)).perform(withCustomConstraints(swipeDown(),  isDisplayingAtLeast(85)))

    }

    @Test
    fun testDataLoaded() {
        val launchActivity = launchActivity()
        val idlingResource = launchActivity.getIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)
        val scrollToPosition = RecyclerViewActions.scrollToPosition<DeliveryItemViewHolder>(10)

        onView(withId(R.id.rl))
            .perform(scrollToPosition).check(ViewAssertions.matches(scrollToPosition.constraints))
        if (idlingResource != null)
            IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun testListItemClick() {
        val launchActivity = launchActivity()
        val idlingResource = launchActivity.getIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)
        val action = RecyclerViewActions.actionOnItemAtPosition<DeliveryItemViewHolder>(
            2,
            ViewActions.click()
        )
        val check = onView(withId(R.id.rl)).perform(action)
//        mCountingTaskExecutorRule.drainTasks(30, TimeUnit.MINUTES)
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        Espresso.pressBack()
//        mCountingTaskExecutorRule.drainTasks(30, TimeUnit.MINUTES)
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        check.check(ViewAssertions.matches(action.constraints))
        if (idlingResource != null)
            IdlingRegistry.getInstance().unregister(idlingResource)
    }


    fun launchActivity(): DeliveryListActivity {
        val launchActivity = activityRule.launchActivity(getIntent())
        EspressoTestUtil.disableAnimations(activityRule)
        return launchActivity
    }

    open fun getIntent(): Intent = Intent()

    private fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController, view: View) {
                action.perform(uiController, view)
            }
        }
    }

}