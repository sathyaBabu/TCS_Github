package com.sathya.tcs_github.REST;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public  static  final String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit = null ;

    // Singleton
    // provider..
    public  static Retrofit getClient() {

        if( retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        // Not a good idea to bring in tail n the base URL lets implement this at MainActivity
      //  ServiceGenerator service = retrofit.create(someInterface.class);

        // Lets provide the retrofit lib..
        return  retrofit ;

    }

}

/*


 */