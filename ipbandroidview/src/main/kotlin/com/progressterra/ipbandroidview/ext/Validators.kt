package com.progressterra.ipbandroidview.ext

fun String.isNameAndSurname(): Boolean = matches(Regex("^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+\$"))

