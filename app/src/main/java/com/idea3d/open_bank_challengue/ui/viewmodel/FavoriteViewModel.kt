package com.idea3d.open_bank_challengue.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.repository.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repo: Repo): ViewModel() {

    fun getFavoriteHeroes() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getFavHeroes())
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}