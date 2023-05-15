package com.progressterra.ipbandroidview.di

import com.progressterra.ipbandroidview.domain.usecase.AskPermissionUseCase
import com.progressterra.ipbandroidview.domain.usecase.CheckPermissionUseCase
import com.progressterra.ipbandroidview.domain.usecase.CopyTextUseCase
import com.progressterra.ipbandroidview.domain.usecase.FetchVersionUseCase
import com.progressterra.ipbandroidview.domain.usecase.OpenMailToUseCase
import com.progressterra.ipbandroidview.domain.usecase.OpenPhoneUseCase
import com.progressterra.ipbandroidview.domain.usecase.OpenUrlUseCase
import com.progressterra.ipbandroidview.domain.usecase.ShareTextUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.AllDocumentsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.AllOrganizationsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.ChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.OrganizationAuditsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.OrganizationsOverviewUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.SendResultOnEmailUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.domain.usecase.location.OpenMapUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StartAudioUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.domain.usecase.partner.FetchPartnerUseCase
import com.progressterra.ipbandroidview.domain.usecase.qr.CreateQr
import com.progressterra.ipbandroidview.domain.usecase.user.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserBirthdayUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserIdUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserNameUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserPhoneUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.LogoutUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.NeedAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.NeedDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UpdatePersonalInfoUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UserExistsUseCase
import org.koin.dsl.module

val useCasesModule = module {

    single<AllDocumentsUseCase> { AllDocumentsUseCase.Base(get(), get(), get(), get()) }

    single<DocumentChecklistUseCase> {
        DocumentChecklistUseCase.Base(
            get(), get(), get(), get()
        )
    }

    single<ChecklistUseCase> { ChecklistUseCase.Base(get(), get(), get(), get()) }

    single<UpdateAnswerUseCase> {
        UpdateAnswerUseCase.Base(
            get(), get(), get(), get(), get(), get()
        )
    }

    single<FetchVersionUseCase> {
        FetchVersionUseCase.Base(
            get()
        )
    }

    single<FinishDocumentUseCase> { FinishDocumentUseCase.Base(get(), get(), get()) }

    single<CreateDocumentUseCase> { CreateDocumentUseCase.Base(get(), get(), get()) }

    single<FetchExistingAuditUseCase> { FetchExistingAuditUseCase.Base(get(), get(), get()) }

    single<EndVerificationChannelUseCase> { EndVerificationChannelUseCase.Base(get(), get()) }

    single<StartVerificationChannelUseCase> { StartVerificationChannelUseCase.Base(get()) }

    single<UpdatePersonalInfoUseCase> { UpdatePersonalInfoUseCase.Base(get(), get(), get()) }

    single<AllOrganizationsUseCase> {
        AllOrganizationsUseCase.Base(get(), get(), get(), get())
    }

    single<OrganizationAuditsUseCase> {
        OrganizationAuditsUseCase.Base(get(), get(), get(), get())
    }

    single<CheckMediaDetailsUseCase> {
        CheckMediaDetailsUseCase.Base(get(), get(), get(), get(), get())
    }

    single<UserExistsUseCase> { UserExistsUseCase.Base() }

    single<FetchUserNameUseCase> { FetchUserNameUseCase.Base() }

    single<FetchUserEmailUseCase> { FetchUserEmailUseCase.Base() }

    single<FetchUserPhoneUseCase> { FetchUserPhoneUseCase.Base() }

    single<FetchUserBirthdayUseCase> { FetchUserBirthdayUseCase.Base() }

    single<NeedDetailsUseCase> { NeedDetailsUseCase.Base() }

    single<NeedAddressUseCase> { NeedAddressUseCase.Base() }

    single<FetchUserAddressUseCase> { FetchUserAddressUseCase.Base() }

    single<CreateQr> { CreateQr.Base() }

    single<SendResultOnEmailUseCase> { SendResultOnEmailUseCase.Base(get(), get(), get(), get()) }

    single<FetchUserIdUseCase> { FetchUserIdUseCase.Base() }

    single<FetchPartnerUseCase> { FetchPartnerUseCase.Base(get(), get(), get(), get(), get()) }

    single<AskPermissionUseCase> { AskPermissionUseCase.Base(get()) }

    single<AudioProgressUseCase> { AudioProgressUseCase.Base(get()) }

    single<MakePhotoUseCase> { MakePhotoUseCase.Base(get(), get(), get(), get()) }

    single<StartAudioUseCase> { StartAudioUseCase.Base(get(), get()) }

    single<PauseAudioUseCase> { PauseAudioUseCase.Base(get()) }

    single<CheckPermissionUseCase> { CheckPermissionUseCase.Base(get()) }

    single<StopRecordingUseCase> { StopRecordingUseCase.Base(get()) }

    single<StartRecordingUseCase> { StartRecordingUseCase.Base(get(), get(), get()) }

    single<OpenMapUseCase> { OpenMapUseCase.Base(get()) }

    single<OpenMailToUseCase> { OpenMailToUseCase.Base(get()) }

    single<CopyTextUseCase> { CopyTextUseCase.Base(get()) }

    single<OpenPhoneUseCase> { OpenPhoneUseCase.Base(get()) }

    single<OpenUrlUseCase> { OpenUrlUseCase.Base(get()) }

    single<ShareTextUseCase> { ShareTextUseCase.Base(get()) }


    single<OrganizationsOverviewUseCase> {
        OrganizationsOverviewUseCase.Base(
            get(), get(), get(), get()
        )
    }

    single<LogoutUseCase> {
        LogoutUseCase.Base()
    }
}