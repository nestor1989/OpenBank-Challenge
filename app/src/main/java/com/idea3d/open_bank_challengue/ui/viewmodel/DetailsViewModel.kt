package com.idea3d.open_bank_challengue.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.repository.Repo
import kotlinx.coroutines.Dispatchers

class DetailsViewModel(private val repo: Repo): ViewModel() {

    private val idHero = MutableLiveData<Long>()

    fun setHero(id:Long){
        idHero.value = id
    }
        val fetchHeroDetails= liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getHeroById(idHero.value))
            }catch (e:Exception){
                emit(Resource.Failure(e))
            }
        }

    val fetchComicDetails= liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getComics(idHero.value))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}