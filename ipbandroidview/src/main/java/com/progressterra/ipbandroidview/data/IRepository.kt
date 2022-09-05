package com.progressterra.ipbandroidview.data


import androidx.paging.PagingData
import com.progressterra.ipbandroidapi.api.iecommerscoreapi.models.CatalogItem
import com.progressterra.ipbandroidapi.api.iecommerscoreapi.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidapi.api.ipbambassador.models.ambassador_status.AmbassadorStatusResponse
import com.progressterra.ipbandroidapi.api.ipbambassador.models.invite_members.InvitingMembersRequest
import com.progressterra.ipbandroidapi.api.ipbmediadatacore.models.UploadImageData
import com.progressterra.ipbandroidview.data.model.BonusesInfo
import com.progressterra.ipbandroidview.ui.chat.utils.Message
import com.progressterra.ipbandroidview.ui.media_data.models.MediaDataUi
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ClientInfo
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ImageUpload
import com.progressterra.ipbandroidview.ui.set_personal_info.models.UserBankData
import com.progressterra.ipbandroidview.ui.user_inviting.models.UserInviteDataUI
import com.progressterra.ipbandroidview.ui.user_inviting.models.UserInviteResultUI
import com.progressterra.ipbandroidview.usecases.goodsPaging.source.StorePagingSource
import com.progressterra.ipbandroidview.utils.ISResult
import com.progressterra.ipbandroidview.utils.SResult
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import java.io.File

interface IRepository {
    suspend fun getAccessToken(): SResult<String>

    interface Chat {
        suspend fun getAccessToken(): SResult<String>

        suspend fun getMessagesList(dialogId: String, page: String): SResult<List<Message>>

        suspend fun sendMessage(
            message: String,
            token: String,
            IDRGDialog: String
        ): SResult<List<Message>>

        suspend fun getDialogInfo(
            clientId: String,
            partnerId: String,
            descriptionDialog: String = "",
            dialogImage: String = ""
        ): SResult<String>
    }

    interface MediaData {
        suspend fun getMediaDataList(idEntity: String): SResult<List<MediaDataUi>>
        suspend fun getMediaData(idMediaData: String): SResult<MediaDataUi>
        suspend fun downloadFile(fileUrl: String): SResult<ResponseBody>
        suspend fun uploadImage(
            image: MultipartBody.Part,
            alias: String? = null
        ): SResult<UploadImageData>

        suspend fun getMediaDataByEntity(idEntity: String):
                SResult<List<com.progressterra.ipbandroidapi.api.ipbmediadatacore.models.MediaData>>
    }

    interface AmbassadorInfo {
        suspend fun getClientInfo(accessToken: String): ISResult<ClientInfo>

        suspend fun uploadImage(
            accessToken: String,
            alias: String = "userdata",
            tag: String = "0",
            imageData: File
        ): ISResult<ImageUpload>

        suspend fun updateClientInfo(
            accessToken: String,
            name: String,
            soname: String,
            patronymic: String
        ): ISResult<ClientInfo>

        suspend fun getBankClientInfo(accessToken: String): ISResult<UserBankData>

        suspend fun updateBankClientInfo(
            accessToken: String,
            bankName: String,
            numberAccount: String,
            bik: String,
            correspondentAccount: String,
            tinOfBank: String,
            kppBank: String,
            clientInn: String
        ): ISResult<UserBankData>

        suspend fun uploadSnilsPhotoUrl(
            snilsPhotoUrl: String,
            accessToken: String
        ): ISResult<Any>

        suspend fun uploadPassportPhotoUrl(
            passportPhotoUrl: String,
            accessToken: String
        ): ISResult<Any>

        suspend fun getAmbassadorStatus(accessToken: String): SResult<AmbassadorStatusResponse>

        suspend fun getAccessToken(): SResult<String>

        suspend fun getContractOfAmbassador(accessToken: String): ISResult<ResponseBody>

        suspend fun uploadAmbassadorContractPhotoUrl(
            accessToken: String,
            urlImage: String
        ): ISResult<Any>

        suspend fun becomeSelfEmployed(accessToken: String): ISResult<AmbassadorStatusResponse>
    }

    interface Store {
        fun getStorePage(
            idCategory: String,
            pageSize: Int = StorePagingSource.DEF_PAGE_SIZE,
            initialLoad: Int = StorePagingSource.DEF_INITIAL_LOAD
        ): Flow<PagingData<RGGoodsInventoryExt>>

        fun updateSearch(search: String)
    }

    interface PromoCode {
        suspend fun getAccessToken(): SResult<String>
        suspend fun setPromoCode(accessToken: String, promoCode: String): SResult<*>
    }

    interface Bonuses {
        suspend fun getBonusesInfo(): SResult<BonusesInfo>
    }

    interface UserInviting {
        suspend fun getInviteInfo(): SResult<UserInviteDataUI>
        suspend fun sendInvites(invitingMembersRequest: InvitingMembersRequest): SResult<UserInviteResultUI>
    }

    interface Catalog {
        suspend fun getCatalog(): SResult<List<CatalogItem>>
    }
}