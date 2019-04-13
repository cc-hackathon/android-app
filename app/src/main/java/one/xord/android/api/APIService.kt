package one.xord.android.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by sami on 10/27/18.
 */

interface APIService {

    @FormUrlEncoded
    @POST("getRequests")
    fun getRequests(@Field("nic") nic: String): Call<ListBodyResponse<Request>>

    @FormUrlEncoded
    @POST("getDataForUser")
    fun getDataForUser(@Field("nic") nic: String): Call<SingleBodyResponse<Data>>

    @FormUrlEncoded
    @POST("writeData")
    fun writeData(@Field("nic") nic: String, @Field("name") name: String, @Field("fatherName") fatherName: String,
                  @Field("gender") gender: String, @Field("country") country: String, @Field("dob") dob: String,
                  @Field("doe") doe: String): Call<BodyResponse>

    @FormUrlEncoded
    @POST("writePerson")
    fun writePerson(@Field("cnic") cnic: String, @Field("phoneNumber") phoneNumber: String): Call<BodyResponse>

    @FormUrlEncoded
    @POST("approveRequest")
    fun approveRequest(@Field("nic") nic: String, @Field("requestId") requestId: String): Call<BodyResponse>

    @FormUrlEncoded
    @POST("declineRequest")
    fun declineRequest(@Field("nic") nic: String, @Field("reqId") reqId: String): Call<BodyResponse>

    @FormUrlEncoded
    @POST("revokeAccess")
    fun revokeAccess(@Field("nic") nic: String, @Field("reqId") reqId: String): Call<BodyResponse>
}