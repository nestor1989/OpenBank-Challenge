package com.idea3d.open_bank_challengue.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.MatcherAssert.assertThat
import com.idea3d.open_bank_challengue.TestCoroutineRule
import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.getOrAwaitValue
import com.idea3d.open_bank_challengue.model.HeroDetails
import com.idea3d.open_bank_challengue.repository.Repo
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {

    @RelaxedMockK
    private lateinit var repo: Repo
    private lateinit var detailsViewModel: DetailsViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        detailsViewModel = DetailsViewModel(repo)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }


    @Test
    fun `when viewmodel is created at the first time, set idHero`() = runTest{
        val number:Long = 1011334
        detailsViewModel.setHero(number)
        assertEquals(number, detailsViewModel.idHero.value)
    }

    @Test
    fun `when fetching results ok then return a list successfully`() {
        // GIVEN
        testCoroutineRule.runBlockingTest {
            // WHEN
            detailsViewModel.fetchHeroDetails()
            // THEN
            assertNotNull(detailsViewModel.fetchHeroDetails())
        }
    }


    @Test
    fun `when calling for results then return loading`() {
        val loading = Resource.Loading<HeroDetails>()
        testCoroutineRule.runBlockingTest {
            detailsViewModel.fetchHeroDetails()
            val result = detailsViewModel.fetchHeroDetails().getOrAwaitValue()
            assertThat(result, instanceOf(loading.javaClass))
        }
    }

    @Test
    fun `when fetching results fails then return an error`() {
        val number:Long = 0
        testCoroutineRule.runBlockingTest {
            detailsViewModel.setHero(number)
            try {
                detailsViewModel.fetchHeroDetails()
            }catch (e: Exception){
                assertThat(e.message, `is`("We don't recognize the parameter id"))
            }
        }

    }
}

