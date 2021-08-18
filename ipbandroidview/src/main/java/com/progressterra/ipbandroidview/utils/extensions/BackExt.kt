package com.progressterra.ipbandroidview.utils.extensions

import com.progressterra.ipbandroidapi.remoteData.models.base.BaseResponse


fun BaseResponse.isSuccess(): Boolean {
    return this.status == 0 || this.result?.status == 0
}