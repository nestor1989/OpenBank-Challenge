package com.idea3d.open_bank_challengue.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.Resource
import com.idea3d.open_bank_challengue.model.ComicDetails
import com.idea3d.open_bank_challengue.repository.Repo
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class DetailsViewModelTest {

    @RelaxedMockK
    private lateinit var repo: Repo


    private lateinit var detailsViewModel: DetailsViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

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
    fun setHeroTest() {

    }

    @Test
    fun getFetchHeroDetails() {
    }

    @Test
    fun getFetchComicDetails() {
        val idHero:Long = 1000000
        val observer = mockk<Observer<com.idea3d.open_bank_challengue.core.vo.Resource<List<ComicDetails>>>>()
        detailsViewModel.fetchComicDetails.observeForever(observer)

        detailsViewModel.setHero(idHero)

        //verify {  observer.onChanged(repo.getComics(idHero)) }

    }
}