package com.user.brayan.pruebatec_todo1.ui.recharge_account

import android.graphics.Bitmap.CompressFormat
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.QRCodes
import com.user.brayan.pruebatec_todo1.repository.AccountsRepository
import com.user.brayan.pruebatec_todo1.repository.Resource
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.inject.Inject


class RechargeAccountViewModel @Inject constructor(repository: AccountsRepository): ViewModel() {
    val typeAccount: MutableLiveData<Accounts> = MutableLiveData()

    val repositories: LiveData<Resource<List<Accounts>>> = repository.loadAccounts("null")

    fun convertData(code: QRCodes): String {
        var gson = Gson()
        var jsonString = gson.toJson(code)

       return generateCode(jsonString)
    }

    private fun generateCode(text: String): String {
        val multiFormatWriter = MultiFormatWriter()

        val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500)
        val barcodeEncoder = BarcodeEncoder()

        val bitmap = barcodeEncoder.createBitmap(bitMatrix)

        val bos = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.PNG, 100, bos)
        val data = bos.toByteArray()

        val path = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )

        val codeQR = File(
            path,
            "${Calendar.getInstance().timeInMillis}.jpg"
        )

        if (codeQR.exists()) {
            codeQR.delete()
        }

        try {
            val fos = FileOutputStream(codeQR.path)
            fos.write(data)
            fos.close();
        } catch (ex: Exception) {
            Log.e("datos", "${ex.message}")
        }

        return codeQR.path
    }
}