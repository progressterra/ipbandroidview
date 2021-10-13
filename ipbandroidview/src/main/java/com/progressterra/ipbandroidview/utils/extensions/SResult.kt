package com.progressterra.ipbandroidview.utils.extensions

import androidx.navigation.NavDirections
import com.progressterra.ipbandroidview.utils.SResult

/**
 *  Преобразует строку в тост
 */
fun String.toToastResult() = SResult.Toast(this)

/**
 *  IdRes в тост
 */
fun Int.toToastResult() = SResult.Toast(this)

/**
 * Any в тост
 */
fun Any.toToastResult() = SResult.Toast(this)

/**
 *  Action в navResult
 */
fun NavDirections.toNavResult() = SResult.NavResult(this)

/**
 *  Любая переменная в SResult
 */
fun <T : Any> T.toSuccessResult() = SResult.Success(this)

/**
 *  Success с необходимостью использовать
 */
fun successHandlingResult() = SResult.Success(Unit).apply {
    isNeedHandle = true
}

/**
 *  Строка в неудачный результат
 */
fun String?.toFailedResult() = SResult.Failed(this, null)

/**
 *  Int в неудачный результат
 */
fun Int.toFailedResult() = SResult.Failed(this, null)

/**
 *  Люая переменная в неудачный результат
 */
fun <T : Any?> T?.toFailedResult() = SResult.Failed(this, null)

/**
 *  Функция для создания "загрузки"
 */
fun loadingResult() = SResult.Loading(null)

/**
 *  Загрузка с данными
 */
fun <T : Any?> T?.toLoadingResult() = SResult.Loading(this)

/**
 *  Функция для создания завершенного результата
 */
fun completedResult() = SResult.Completed

/**
 *  Failed без результатов
 */
fun emptyFailed() = SResult.Failed(null, null)