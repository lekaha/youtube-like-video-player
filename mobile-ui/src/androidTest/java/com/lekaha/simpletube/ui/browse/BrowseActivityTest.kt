package com.lekaha.simpletube.ui.browse

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.ui.R
import com.lekaha.simpletube.ui.test.TestApplication
import com.lekaha.simpletube.ui.test.factory.SimpletubeFactory
import com.lekaha.simpletube.ui.test.util.RecyclerViewMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BrowseActivityTest {

    @Rule @JvmField
    val activity = ActivityTestRule<BrowseActivity>(BrowseActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        stubSimpletubeRepositoryGetSimpletubes(Single.just(SimpletubeFactory.makeSimpletubeList(2)))
        activity.launchActivity(null)
    }

    @Test
    fun simpletubesDisplay() {
        val simpletubes = SimpletubeFactory.makeSimpletubeList(1)
        stubSimpletubeRepositoryGetSimpletubes(Single.just(simpletubes))
        activity.launchActivity(null)

        checkSimpletubeDetailsDisplay(simpletubes[0], 0)
    }

    @Test
    fun simpletubesAreScrollable() {
        val simpletubes = SimpletubeFactory.makeSimpletubeList(20)
        stubSimpletubeRepositoryGetSimpletubes(Single.just(simpletubes))
        activity.launchActivity(null)

        simpletubes.forEachIndexed { index, simpletube ->
            onView(withId(R.id.recycler_browse)).perform(RecyclerViewActions.
                    scrollToPosition<RecyclerView.ViewHolder>(index))
            checkSimpletubeDetailsDisplay(simpletube, index) }
    }

    private fun checkSimpletubeDetailsDisplay(simpletube: Simpletube, position: Int) {
        onView(RecyclerViewMatcher.withRecyclerView(R.id.recycler_browse).atPosition(position))
                .check(matches(hasDescendant(withText(simpletube.name))))
        onView(RecyclerViewMatcher.withRecyclerView(R.id.recycler_browse).atPosition(position))
                .check(matches(hasDescendant(withText(simpletube.title))))
    }

    private fun stubSimpletubeRepositoryGetSimpletubes(single: Single<List<Simpletube>>) {
        whenever(TestApplication.appComponent().simpletubeRepository().getSimpletubes())
                .thenReturn(single)
    }

}