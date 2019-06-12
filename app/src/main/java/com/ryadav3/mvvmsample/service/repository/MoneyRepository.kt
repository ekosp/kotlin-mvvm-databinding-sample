package com.ryadav3.mvvmsample.service.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.ryadav3.mvvmsample.service.model.Money

class MoneyRepository {

    companion object {
        private var moneyRepository: MoneyRepository? = null
        private var context: Context? = null

        @Synchronized
        @JvmStatic
        fun getInstance(context: Context): MoneyRepository {
            this.context = context
            if (moneyRepository == null) moneyRepository = MoneyRepository()
            return moneyRepository!!
        }
    }

    fun calculate(subtotal: Int, diskon: Int): LiveData<Money> {

        val moneyResult = MutableLiveData<Money>()
        val total = subtotal - (subtotal * diskon / 100)
        val pajak = 10
        val jumlahTotal = total + (total * pajak / 100)

        val m = Money(total, pajak, jumlahTotal)
        moneyResult.value = m

        return moneyResult
    }
}