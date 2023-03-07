package com.progressterra.ipbandroidview.ext

fun String.isNameAndSurname(): Boolean = matches(Regex("^[a-zA-Z]+ [a-zA-Z]+$"))

