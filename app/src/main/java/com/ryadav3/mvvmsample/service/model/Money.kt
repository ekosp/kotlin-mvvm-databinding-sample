package com.ryadav3.mvvmsample.service.model


data class Money(

        var total: Int = 0,
        var pajak: Int = 0,
        var jumlahTotal: Int = 0
) {
    override fun toString(): String {
        return "Money(total=$total, pajak=$pajak, jumlahTotal=$jumlahTotal)"
    }
}