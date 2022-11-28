package com.dikkulah.retrofit;

import com.dikkulah.exception.DailyApiException;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class RetrofitCommonGenerics {

    public static <T> T retrofitGenerics(Call<T> request) {
        try {
            Response<T> response = request.execute();
            if (!response.isSuccessful()) {
                throw new DailyApiException(Objects.requireNonNull(response.errorBody()).string());
            }
            return response.body();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException(ioException.getCause());
        }
    }
}
