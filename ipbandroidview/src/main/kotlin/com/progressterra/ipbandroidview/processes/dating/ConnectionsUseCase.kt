package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidview.pages.connections.ConnectionsScreenState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.CacheUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

interface ConnectionsUseCase : CacheUseCase<ConnectionsScreenState> {

    suspend operator fun invoke()

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val incomingConnectionsUseCase: IncomingConnectionsUseCase,
        private val pendingConnectionsUseCase: PendingConnectionsUseCase,
        private val successConnectionsUseCase: SuccessConnectionsUseCase,
        makeToastUseCase: MakeToastUseCase, manageResources: ManageResources
    ) : ConnectionsUseCase,
        AbstractCacheTokenUseCase<ConnectionsScreenState>(obtainAccessToken, makeToastUseCase,
            manageResources
        ) {

        override suspend fun invoke() {
            withCache {
                val incoming = incomingConnectionsUseCase().getOrThrow()
                val pending = pendingConnectionsUseCase().getOrThrow()
                val success = successConnectionsUseCase().getOrThrow()
                ConnectionsScreenState(
                    incoming = incoming,
                    success = success,
                    pending = pending,
                    screen = StateColumnState(
                        id = "",
                        state = ScreenState.SUCCESS
                    )
                )
            }
        }
    }
}
