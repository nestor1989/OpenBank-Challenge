package com.idea3d.open_bank_challengue.ui.viewmodel

import androidx.lifecycle.*
import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.repository.Repo
import kotlinx.coroutines.Dispatchers

class MainViewModel (private val repo: Repo): ViewModel() {

    private val heroData = MutableLiveData<String>()

    fun setHero(heroName:String){
        heroData.value = heroName
    }

    init{
        setHero("a")
    }

    val fetchHerosList = heroData.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getHeroList(it))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

}
