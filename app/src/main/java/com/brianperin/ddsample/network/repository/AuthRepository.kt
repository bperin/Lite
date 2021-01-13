package com.brianperin.ddsample.network.repository

import com.brianperin.ddsample.network.AuthRequest
import com.brianperin.ddsample.network.BaseDataSource
import com.brianperin.ddsample.network.DashApiClient
import com.brianperin.ddsample.network.DashService
import com.brianperin.ddsample.util.Constants


class AuthRepository() : BaseDataSource() {

    var service: DashService

    init {
        val client = DashApiClient()
        client.setEndpoint(Constants.HOST)
        service = client.dashService

    }

    suspend fun authenticate(authRequest: AuthRequest) = getResult { service.authenticate(authRequest) }
}