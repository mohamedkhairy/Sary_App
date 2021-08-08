package com.example.sary_app.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sary_app.model.BannerModel
import com.example.sary_app.model.CatalogResult
import com.example.sary_app.model.CatalogUiType
import com.example.sary_app.model.toCatalogType
import com.example.sary_app.repository.Repository
import com.example.sary_app.utils.DataState
import com.example.sary_app.utils.Types
import com.example.sary_app.utils.ViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.Observable
import io.reactivex.Observable.fromIterable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


import kotlinx.coroutines.rx2.SchedulerCoroutineDispatcher
import kotlinx.coroutines.rx2.rxFlowable
import kotlinx.coroutines.rx2.rxSingle
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val _bannerState: MutableLiveData<DataState<List<String>?>> =
        MutableLiveData()

    val bannerState: LiveData<DataState<List<String>?>>
        get() = _bannerState

    private val _catalogState: MutableLiveData<DataState<ViewType?>> =
        MutableLiveData()

    val catalogState: LiveData<DataState<ViewType?>>
        get() = _catalogState

    @SuppressLint("CheckResult")
    fun getSlideHomeBanner() {
        rxSingle() {
            repository.getHomeBanner()
        }
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                disposable.add(it)
                DataState.Loading
                _bannerState.postValue(DataState.Loading)
            }
            .map {
                DataState.Success(it)
            }
            .doOnError {
                val error = DataState.Error(it.message)
                _bannerState.postValue(error)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _bannerState.value = it
            }, {
                _bannerState.value = DataState.Error(it.message)
            })

    }


    @SuppressLint("CheckResult")
    fun getHomeCatalog() {
        rxSingle {
            repository.getHomeCatalog()
        }.subscribeOn(Schedulers.io())
            .doOnSubscribe {
                disposable.add(it)
                DataState.Loading
                _catalogState.postValue(DataState.Loading)
            }
            .toObservable()
            .flatMap(::fromIterable)
            .map {
                val type = it.toCatalogType()
                DataState.Success(type)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _catalogState.value = it
            }, {
                _catalogState.value = DataState.Error(it.message)
            })
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}