package com.mahmud.ramadan2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    TextView dateenglishTv, dateRamadanTv, sehriTimeTv, iftarTimeTv, fajarTimeTv, duhrTimeTv,asarTimeTv,magribTimeTv,eshaTimeTV;
    TimingsAPI timingsApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dateenglishTv = (TextView) findViewById(R.id.dateEnglishTv);
        dateRamadanTv = (TextView) findViewById(R.id.dateRamadan);
        sehriTimeTv = (TextView) findViewById(R.id.sehriTimeTv);
        iftarTimeTv = (TextView) findViewById(R.id.iftarTimeTV);
        fajarTimeTv = (TextView) findViewById(R.id.fajarTimeTv);
        duhrTimeTv = (TextView) findViewById(R.id.duhrTimeTv);
        asarTimeTv = (TextView) findViewById(R.id.asarTimeTV);
        magribTimeTv = (TextView) findViewById(R.id.magribTimeTv);
        eshaTimeTV = (TextView) findViewById(R.id.eshaTimeTV);


        networkLibraryInitializer();
        getTimingsData();

    }

    private void getTimingsData() {
        Call<TimingsMain>timingsMainCall = timingsApi.getTimingsData();
        timingsMainCall.enqueue(new Callback<TimingsMain>() {
            @Override
            public void onResponse(Call<TimingsMain> call, Response<TimingsMain> response) {
              TimingsMain timingsMain = response.body();

                dateenglishTv.setText(timingsMain.getData().getDate().getReadable());
               // dateRamadanTv.setText(timingsMain.getData().getDate().getTimestamp());

                sehriTimeTv.setText("Sehri : "+timingsMain.getData().getTimings().getImsak());
                iftarTimeTv.setText("Iftar : "+timingsMain.getData().getTimings().getSunset());

                fajarTimeTv.setText(timingsMain.getData().getTimings().getFajr());
                duhrTimeTv.setText(timingsMain.getData().getTimings().getDhuhr());
                asarTimeTv.setText(timingsMain.getData().getTimings().getAsr());
                magribTimeTv.setText(timingsMain.getData().getTimings().getMaghrib());
                eshaTimeTV.setText(timingsMain.getData().getTimings().getIsha());

            }

            @Override
            public void onFailure(Call<TimingsMain> call, Throwable t) {

            }
        });
    }

    private void networkLibraryInitializer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.aladhan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        timingsApi = retrofit.create(TimingsAPI.class);
    }
}
