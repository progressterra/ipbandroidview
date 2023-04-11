package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidview.shared.activity.MakePhotoContract
import com.progressterra.ipbandroidview.shared.activity.ManagePermissionContract
import com.progressterra.ipbandroidview.shared.activity.StartActivityContract
import org.koin.dsl.binds
import org.koin.dsl.module

val sharedModule = module {

    single {
        ManagePermissionContract.Base()
    }.binds(
        arrayOf(
            ManagePermissionContract.Client::class,
            ManagePermissionContract.Activity::class
        )
    )

    single {
        StartActivityContract.Base()
    }.binds(arrayOf(StartActivityContract.Client::class, StartActivityContract.Activity::class))

    single {
        MakePhotoContract.Base()
    }.binds(arrayOf(MakePhotoContract.Activity::class, MakePhotoContract.Client::class))
}