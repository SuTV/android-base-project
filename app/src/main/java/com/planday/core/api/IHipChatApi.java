package com.planday.core.api;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Su on 12/28/2015.
 */

/**
 * See https://www.hipchat.com/docs/api for documentation.
 */

public interface IHipChatApi {

    String ENDPOINT = "https://api.hipchat.com";
    String TOKEN = "Bearer <room auth token>";
    String MEMORY_LEAK_CHANNEL = "<room number or id>";

    /**
     * @param token          A room notification token, generated via a room's admin page on the HipChat website
     * @param id_or_name     The id or name of the room.
     *                       Valid length range: 1 - 100.
     * @param message        The message body.
     *                       Valid length range: 1 - 10000.
     * @param message_format Determines how the message is treated by our server and rendered inside HipChat applications
     *                       html - Message is rendered as HTML and receives no special treatment. Must be valid HTML and entities must be escaped (e.g.: '&amp;' instead of '&'). May contain basic tags: a, b, i, strong, em, br, img, pre, code, lists, tables.
     *                       text - Message is treated just like a message sent by a user. Can include @mentions, emoticons, pastes, and auto-detected URLs (Twitter, YouTube, images, etc).
     *                       Valid values: html, text.
     *                       Defaults to 'html'.
     * @param color          Background color for message.
     *                       Valid values: yellow, green, red, purple, gray, random.
     *                       Defaults to 'yellow'.
     * @param notify         Whether this message should trigger a user notification (change the tab color, play a sound, notify mobile phones, etc). Each recipient's notification preferences are taken into account.
     *                       Defaults to false.
     * @return
     */
    @FormUrlEncoded
    @POST("/v2/room/{id_or_name}/notification")
    Observable<String> postMessage(@Header("Authorization") String token, @Path("id_or_name") String id_or_name, @Field("message") String message,
                       @Field("message_format") String message_format, @Field("color") String color,
                       @Field("notify") boolean notify);
}
