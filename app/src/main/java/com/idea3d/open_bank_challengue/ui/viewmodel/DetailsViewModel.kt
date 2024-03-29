package com.idea3d.open_bank_challengue.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.repository.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repo: Repo): ViewModel() {

    val idHero = MutableLiveData<Long>()

    fun setHero(id:Long){
        idHero.value = id
    }


    fun fetchHeroDetails()= liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getHeroById(idHero.value))
            }catch (e:Exception){
                emit(Resource.Failure(e))
            }
        }

    fun fetchComicDetails()= liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getComics(idHero.value))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}