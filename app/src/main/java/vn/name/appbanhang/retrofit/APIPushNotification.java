package vn.name.appbanhang.retrofit;



import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import vn.name.appbanhang.model.NotiResponse;
import vn.name.appbanhang.model.NotiSendData;

public interface APIPushNotification {
    @Headers(
            {
                    "content-type: application/json",
                    "authorization: key=AAAAaRarJoE:APA91bECtKF6gYr3jGdeJAy9LT4ZrYfph1NUW_Bfux1IFW93ffpdui3h52ireRgtQH44lbt-lD4pkjlqhuaszEvQ0b1Wz94q0WvhMlUs6bIcw-ojILWncfXBK3kaxcaXvOzko1vpMAAl"
            }
    )
    @POST("fcm/send")

    Observable<NotiResponse> sendNotification(@Body NotiSendData data);


}
