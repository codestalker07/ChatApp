package com.example.finalapp.Fragments;

import com.example.finalapp.Notifications.MyResponse;
import com.example.finalapp.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
        @Headers(
                {
                        "Content-Type:application/json",
                        "Authorization:key=AAAA2l47rBg:APA91bFk__KLd84w8QZGrSULHtNKOphQuyviMM3u8FiA-8bd6xpCjzL_PwpcE7lrQcMpOty09kcztoB2a83nEskg1NOkQx9LzTpe0mGer-vCzzCaLF9RuQSvjuNsFOOsGsJQIYhp-1WR"
                }
        )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
