/*
package com.sotdogn.bldogkepd.httprequest;


import static com.luciada.modids.MyAdZOne.app_failData;

import android.content.Context;

import com.luciada.modids.AESSUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class GetRequestList {


    private Context context;
    private HttpGetResponsable httpGetResponsable;
    String app_failDatads;

    public GetRequestList(Context context) {
        this.context = context;
    }
    public void sendRequest(final String url, RequestParams requestParams, final String datalist) {
        try {
            app_failDatads = AESSUtils.Logd(app_failData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(app_failDatads, requestParams, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                callBackToCaller(response, datalist);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                callBackToCaller(null, datalist);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                callBackToCaller(null, datalist);
            }


            @Override
            public void onFinish() {
                super.onFinish();
            }
        });

    }


    private void callBackToCaller(JSONObject jsonObject, String httpurl) {
        httpGetResponsable = (HttpGetResponsable) context;
        httpGetResponsable.onHttpGetResponse(jsonObject, httpurl);

    }


    public interface HttpGetResponsable {
        void onHttpGetResponse(JSONObject jsonObject, String httpurl);
    }

}
*/
