package com.example.sportaandcharity.network

import com.example.sportaandcharity.network.AuthResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID


interface MainApi {
     @GET("list")
     suspend fun getServerResponse(): AuthResponseDto

     @PUT("list/{id}")
     suspend fun editTodoItemToInternet(
         @Header("X-Last-Known-Revision") revision: Int,
         @Path("id") id: UUID,
         @Body body: AuthRequestDto
     ): Response<AuthResponseDto>

     @DELETE("list/{id}")
     suspend fun deleteTodoItem(
         @Header("X-Last-Known-Revision") revision: Int,
         @Path("id") id: UUID
     ): Response<AuthResponseDto>

     @GET("list")
     suspend fun downloadTodoList(): Response<AuthResponseDto>

     @PATCH("list")
     suspend fun updateServerFromDb(@Header("X-Last-Known-Revision") revision: Int,
                                    @Body body: AuthResponseDto
     ): Response<AuthResponseDto>

     @POST("list")
     suspend fun loadTodoItem(@Header("X-Last-Known-Revision") revision: Int, @Body body:AuthRequestDto)
             : Response<AuthResponseDto>


}