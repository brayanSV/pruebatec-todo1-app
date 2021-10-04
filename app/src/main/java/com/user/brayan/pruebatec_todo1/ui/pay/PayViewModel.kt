package com.user.brayan.pruebatec_todo1.ui.pay

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.model.QRCodes
import com.user.brayan.pruebatec_todo1.repository.HistoryAccountsRepository
import com.user.brayan.pruebatec_todo1.repository.Resource
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PayViewModel @Inject constructor(repository: HistoryAccountsRepository): ViewModel() {
    private val _QRCode: MutableLiveData<String> = MutableLiveData()
    val QRCode: LiveData<String> get() = _QRCode

    val decodeQRCode: MutableLiveData<QRCodes> = MutableLiveData()

    fun setQR(path: String) {
        if (_QRCode.value == path) {
            return
        }

        _QRCode.value = path
    }

    fun convertJsonToData() {
        val gson = Gson()
        decodeQRCode.value = gson.fromJson(QRCode.value, QRCodes::class.java)
    }

    private val _transfer: MutableLiveData<HistoryAccounts> = MutableLiveData()
    private val transfer: LiveData<HistoryAccounts> get() = _transfer

    val history: LiveData<Resource<String>> = Transformations.switchMap(transfer) {
        repository.payBill(it)
    }

    fun createTransfer() {
        val format = SimpleDateFormat("yyyy/MM/dd", Locale("es-CO"))
        val dateNow = Date(Calendar.getInstance().timeInMillis)
        val date = format.format(dateNow)
        val amount = BigDecimal(decodeQRCode.value!!.amount)
        val amountSel = amount.multiply(BigDecimal("-1"))

        _transfer.value = HistoryAccounts(
            0,
            date,
            decodeQRCode.value!!.description,
            "transfer",
            amountSel.toString(),
            decodeQRCode.value!!.accountID
        )
    }
}