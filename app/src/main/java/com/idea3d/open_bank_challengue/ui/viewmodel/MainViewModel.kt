package com.idea3d.open_bank_challengue.ui.viewmodel

import androidx.lifecycle.*
import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.model.HeroEntity
import com.idea3d.open_bank_challengue.repository.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repo): ViewModel() {

    val heroData = MutableLiveData<String>()

    fun setHero(heroName:String){
        heroData.value = heroName
    }

    init{
        setHero("a")
    }

    fun fetchHerosList() = heroData.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getHeroList(it))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun saveHero(hero:HeroEntity){
        viewModelScope.launch {
            repo.insertHero(hero)
        }
    }

}
