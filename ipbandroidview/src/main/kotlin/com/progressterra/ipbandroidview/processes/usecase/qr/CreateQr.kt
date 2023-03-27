package com.progressterra.ipbandroidview.processes.usecase.qr

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.graphics.set
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

interface CreateQr {

    suspend operator fun invoke(content: String): Bitmap

    class Base : CreateQr {

        override suspend fun invoke(content: String): Bitmap {
            val qrSize = 1024
            val bits = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, qrSize, qrSize)
            val bitmap = Bitmap.createBitmap(qrSize, qrSize, Bitmap.Config.RGB_565)
            for (x in 0 until qrSize) {
                for (y in 0 until qrSize) {
                    bitmap[x, y] = if (bits[x, y]) Color.BLACK else Color.WHITE
                }
            }
            return bitmap
        }
    }
}