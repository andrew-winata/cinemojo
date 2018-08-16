package com.cinemojo.core.util;

import retrofit2.Call;

public interface RetrofitUtils {
    static void cancelCall(Call<?> retrofitCall) {
        if (retrofitCall != null && retrofitCall.isExecuted()) {
            retrofitCall.cancel();
        }
    }
}
