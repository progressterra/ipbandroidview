package com.progressterra.ripcurl.utils.view.image_view

import android.graphics.*
import com.squareup.picasso.Transformation

class CircleTransform : Transformation {

    override fun transform(source: Bitmap): Bitmap {
        val size: Int = source.width.coerceAtMost(source.height)

        val x: Int = (source.width - size) / 2
        val y: Int = (source.height - size) / 2

        val squaredBitmap: Bitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source)
            source.recycle()

        val bitmap = Bitmap.createBitmap(size, size, source.config)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true

        val r: Float = size / 2F
        canvas.drawCircle(r, r, r, paint)
        squaredBitmap.recycle()

        return bitmap
    }

    override fun key() = "circle"
}