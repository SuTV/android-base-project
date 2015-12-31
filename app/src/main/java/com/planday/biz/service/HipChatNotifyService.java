package com.planday.biz.service;

import com.planday.core.api.IHipChatApi;
import com.planday.utils.RetrofitUtils;
import com.squareup.leakcanary.AnalysisResult;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.HeapDump;

import timber.log.Timber;

/**
 * Created by Su on 12/28/2015.
 */

public final class HipChatNotifyService extends DisplayLeakService {

    private IHipChatApi hipChatApi;

    private static String classSimpleName(String className) {
        int separator = className.lastIndexOf('.');
        return separator == -1 ? className : className.substring(separator + 1);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        hipChatApi = RetrofitUtils.create(IHipChatApi.class, IHipChatApi.ENDPOINT);
    }

    @Override
    protected void afterDefaultHandling(HeapDump heapDump, AnalysisResult result, String leakInfo) {
        if (!result.leakFound || result.excludedLeak) {
            return;
        }
        String name = classSimpleName(result.className);
        if (!heapDump.referenceName.equals("")) {
            name += "(" + heapDump.referenceName + ")";
        }

        try {
            hipChatApi.postMessage(IHipChatApi.TOKEN, IHipChatApi.MEMORY_LEAK_CHANNEL,
                    String.format("%s has leaked:\n%s", name, leakInfo), "text", "yellow", true);
        } catch (Exception e) {
            Timber.e( e, "Error when uploading heap dump");
        }
    }
}
