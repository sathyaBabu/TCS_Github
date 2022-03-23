package com.sathya.tcs_github.REST;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OKhttp_ServiceGenerator {

    public  static  final String API_BASE_URL = "https://api.github.com/";
   // private static Retrofit retrofit = null ;

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private  static Interceptor logging = interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    // We are curious to know if the data is been rendered in time or not
    // Its about adding an SSL certificate into HTTP
    // https://stackoverflow.com/questions/30779533/how-to-make-https-request-with-ssl-certificate-in-retrofit

// provider.  httpClient

    private  static OkHttpClient httpClient = new OkHttpClient.Builder()
            .readTimeout(300, TimeUnit.MILLISECONDS)
            .writeTimeout(300,TimeUnit.MILLISECONDS)

//            .callTimeout(2000, TimeUnit.MILLISECONDS)
//            .connectTimeout( 2000,TimeUnit.MILLISECONDS)
            .addInterceptor( logging )
            .build();

    // we need to inject the provider into our retrofit builder..
    // we are provoding retrofit which has OKhttp, ssl, Gson

    private  static  Retrofit.Builder builder =
            new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public  static  <S> S createService( Class<S> serviceClass) {

        Retrofit retrofit = builder.client( httpClient).build();
        return retrofit.create(serviceClass);

    }

}
