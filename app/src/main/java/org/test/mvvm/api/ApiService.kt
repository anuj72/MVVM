package org.test.mvvm.api

import org.test.mvvm.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("placeholder/user/{userId}")
    suspend fun getUser(
        @Path("userId") userId:String
    ): User
}