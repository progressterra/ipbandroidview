package asd.android.networkrepository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

import com.progressterra.android.api.repository.models.bonuses_info.BonusesInfo
import com.progressterra.android.api.repository.models.bonuses_info.Data
import java.text.SimpleDateFormat
import java.util.*



object GeneralInfoResponseConverter {

    // ковертируем дату в нужный нам формат для отображения
    private fun convertDate(dateString: String): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        try {
            val parsedDate = simpleDateFormat.parse(dateString)
            val calendar = GregorianCalendar()
            calendar.time = parsedDate
            val month = ((calendar.get(Calendar.MONTH) + 1) % 13).toString()
            val formattedMonth = if (month.length == 1) "0$month" else month
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            return "$day.$formattedMonth"
        } catch (e: Exception) {
            Log.v("dateParse", e.toString())
        }
        return ""
    }

    fun convert(data: Data): BonusesInfo {
        return BonusesInfo(
            currentQuantity = data.currentQuantity.toInt(),
            dateBurning = convertDate(data.dateBurning),
            forBurningQuantity = data.forBurningQuantity.toInt(),
            typeBonusName = data.typeBonusName
        )
    }

}