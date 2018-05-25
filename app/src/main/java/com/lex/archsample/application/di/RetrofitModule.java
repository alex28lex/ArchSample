package com.lex.archsample.application.di;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lex.archsample.application.manager.SessionManager;
import com.lex.archsample.application.rest.RestClient;
import com.lex.archsample.R;
import com.lex.archsample.application.rest.RestClient;
import com.lex.archsample.application.manager.SessionManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class RetrofitModule {

    @Singleton
    @Provides
    protected RestClient provideApiClient(Retrofit retrofit) {
        return retrofit.create(RestClient.class);
    }

    @Singleton
    @Provides
    protected Retrofit provideRetrofit(Context context, Gson gson, OkHttpClient client) {
        final String baseUrl = context.getString(R.string.url_base);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    protected Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Singleton
    @Provides
    protected OkHttpClient provideOkHttpClient(Context context,
                                               HttpLoggingInterceptor loggingInterceptor) {
        final long connectTimeout = context.getResources().getInteger(R.integer.connect_timeout_seconds);
        final long readTimeout = context.getResources().getInteger(R.integer.read_timeout_seconds);
        final long writeTimeout = context.getResources().getInteger(R.integer.write_timeout_seconds);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    protected HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Singleton
    @Provides
    protected Interceptor provideAuthInterceptor(Context context,
                                                 SessionManager sessionManager) {
        return chain -> {
            final Request originalRequest = chain.request();

            final String authHeader = context.getString(R.string.auth_key);
            final Request authorizedRequest = originalRequest.newBuilder()
                    .header(authHeader, sessionManager.getToken())
                    .build();

            return chain.proceed(authorizedRequest);
        };
    }
}
