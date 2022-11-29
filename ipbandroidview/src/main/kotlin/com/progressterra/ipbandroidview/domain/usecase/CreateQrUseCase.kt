package com.progressterra.ipbandroidview.domain.usecase

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.graphics.set
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

interface CreateQrUseCase {

    suspend operator fun invoke(content: String): Bitmap

    class Base : CreateQrUseCase {

        override suspend fun invoke(content: String): Bitmap {
            val qrSize = 512
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