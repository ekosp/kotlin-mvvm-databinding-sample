package com.ryadav3.mvvmsample.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ryadav3.mvvmsample.BR
import com.ryadav3.mvvmsample.R
import com.ryadav3.mvvmsample.databinding.ActivityMoneyBinding
import com.ryadav3.mvvmsample.view.callback.MoneyActivityCallback
import com.ryadav3.mvvmsample.viewmodel.MoneyViewModel
import es.dmoral.toasty.Toasty

class MoneyActivity : AppCompatActivity(), MoneyActivityCallback {

    private var activityMoneyBinding: ActivityMoneyBinding? = null
    private var moneyViewModel: MoneyViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMoneyBinding = DataBindingUtil.setContentView(this, R.layout.activity_money)
        activityMoneyBinding?.moneyActivityCallback = this
        moneyViewModel = ViewModelProviders.of(this, MoneyViewModel.Factory(this)).get(MoneyViewModel::class.java)
    }

    override fun onCalculateClick(view: View) {
        observeMoney(activityMoneyBinding?.edtSubTotal?.text.toString().toInt(),
                activityMoneyBinding?.edtDiskon?.text.toString().toInt())
    }

    private fun observeMoney(subTotal: Int, diskon: Int) {
        moneyViewModel?.calculateMoney(subTotal, diskon)?.observe(this, Observer { money ->
            if (money != null) {
                Toasty.success(applicationContext, "Hitung Uang Success", Toast.LENGTH_SHORT).show()

                activityMoneyBinding?.setVariable(BR.money, money)
                activityMoneyBinding?.executePendingBindings()


            } else {
                Toasty.error(applicationContext, "Login Failed, please try again", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
