package com.idea3d.open_bank_challengue.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.idea3d.open_bank_challengue.TestCoroutineRule
import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.getOrAwaitValue
import com.idea3d.open_bank_challengue.model.Hero
import com.idea3d.open_bank_challengue.model.HeroDetails
import com.idea3d.open_bank_challengue.repository.Repo
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.*
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class MainViewModelTest{
    @RelaxedMockK
    private lateinit var repo: Repo
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(repo)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }


    @Test
    fun `when viewmodel is created at the first time, set idHero`() = runTest{
        val name = "a"
        mainViewModel.setHero(name)
        Assert.assertEquals(name, mainViewModel.heroData.value)
    }

    @Test
    fun `when fetching results ok then return a list successfully`() {
        // GIVEN
        testCoroutineRule.runBlockingTest {
            // WHEN
            mainViewModel.fetchHerosList()
            // THEN
            Assert.assertNotNull(mainViewModel.fetchHerosList())
        }
    }


    @Test
    fun `when calling for results then return loading`() {
        val loading = Resource.Loading<Hero>()
        testCoroutineRule.runBlockingTest {
            mainViewModel.fetchHerosList()
            val result = mainViewModel.fetchHerosList().getOrAwaitValue()
            MatcherAssert.assertThat(result, Matchers.instanceOf(loading.javaClass))
        }
    }

    @Test
    fun `when fetching results fails then return an error`() {
        testCoroutineRule.runBlockingTest {
            mainViewModel.setHero("!")
            try {
                mainViewModel.fetchHerosList()
            }catch (e: Exception){
                MatcherAssert.assertThat(
                    e.message,
                    Matchers.`is`("We don't recognize the parameter id")
                )
            }
        }

    }
}