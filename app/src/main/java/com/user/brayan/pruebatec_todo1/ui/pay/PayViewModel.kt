package com.user.brayan.pruebatec_todo1.ui.pay

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.model.QRCodes
import com.user.brayan.pruebatec_todo1.repository.HistoryAccountsRepository
import com.user.brayan.pruebatec_todo1.repository.Resource
import com.user.brayan.pruebatec_todo1.utils.AbsentLiveData
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PayViewModel @Inject constructor(repository: HistoryAccountsRepository): ViewModel() {
    val decodeQRCode: MutableLiveData<QRCodes> = MutableLiveData()

    fun convertJsonToData(qrCode: String) {
        decodeQRCode.value = Gson().fromJson(qrCode, QRCodes::class.java)
    }

    private val _transfer: MutableLiveData<Pay> = MutableLiveData()
    private val transfer: LiveData<Pay> get() = _transfer

    fun createTransfer(): HistoryAccounts {
        val format = SimpleDateFormat("yyyy/MM/dd", Locale("es-CO"))
        val dateNow = Date(Calendar.getInstance().timeInMillis)
        val date = format.format(dateNow)
        val amount = BigDecimal(decodeQRCode.value!!.amount)
        val amountSel = amount.multiply(BigDecimal("-1"))

        return HistoryAccounts(
            0,
            date,
            decodeQRCode.value!!.description,
            "transfer",
            amountSel.toString(),
            decodeQRCode.value!!.accountID
        )
    }

    val history: LiveData<Resource<String>> = Transformations.switchMap(transfer) {
        repository.payBill(it.history, it.bearerToken)
    }

    fun setData(history: HistoryAccounts, bearerToken: String) {
        val update = Pay(history, bearerToken)

        if (_transfer.value == update) {
            return
        }

        _transfer.value = update
    }

    data class Pay(val history: HistoryAccounts, val bearerToken: String) {
        fun<T> ifExists(f: (HistoryAccounts, String) -> LiveData<T>): LiveData<T> {
            return if (bearerToken.isBlank()) {
                AbsentLiveData.create()
            } else {
                f(history, bearerToken)
            }
        }
    }
}