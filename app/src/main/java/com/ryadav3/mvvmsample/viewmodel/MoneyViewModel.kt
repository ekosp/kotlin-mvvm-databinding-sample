package com.ryadav3.mvvmsample.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.ryadav3.mvvmsample.service.model.Money
import com.ryadav3.mvvmsample.service.repository.MoneyRepository

class MoneyViewModel(val context: Context) : ViewModel() {

    fun calculateMoney(subTotal: Int, diskon: Int): LiveData<Money> {
        return MoneyRepository.getInstance(context).calculate(subTotal, diskon)
    }

    override fun onCleared() {
        super.onCleared()
    }

    class Factory(val context: Context) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return MoneyViewModel(context) as T
        }
    }
}