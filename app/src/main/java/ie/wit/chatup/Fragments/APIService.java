package ie.wit.chatup.Fragments;


import ie.wit.chatup.Notifications.MyResponse;
import ie.wit.chatup.Notifications.Sender;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService
{
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAc0_i0c4:APA91bHP6igjiQSTE1TmSc0arQp-XzZo8tLLaF2HbLbIHvjsBqwCD8Py3soDxpc33vn2lrNlwos6MSsmayYg6s0JCgX1pjQ4-Eo-Kb8VxW4Mk9MR-wjN1dqTdZVpTZU-e_lQ2u9Y8i-A"
    })

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}

