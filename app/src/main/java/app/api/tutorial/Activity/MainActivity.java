package app.api.tutorial.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import app.api.tutorial.Response.Data;
import app.api.tutorial.Response.Setting;
import app.api.tutorial.Service.RetrofitClient;
import app.api.tutorial.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Data> getData = RetrofitClient.getInstance().getApi().getUser(binding.etUserName.getText().toString());
                getData.enqueue(new Callback<Data>() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        if (response.isSuccessful()) {
                            Data data = response.body();
                            if (data != null) {
                                binding.tvUserData.setText(String.format(
                                        "Name: %s \n Email: %s \n Phone: %d", data.getUser().getName(), data.getUser().getEmail(), data.getUser().getPhone()
                                ));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Call<Setting> getData = RetrofitClient.getInstance().getApi().getData();
        getData.enqueue(new Callback<Setting>() {
            @Override
            public void onResponse(@NonNull Call<Setting> call, @NonNull Response<Setting> response) {
                if (response.isSuccessful()) {
                    Setting setting = response.body();
                    if (setting != null) {
                        AdView adView = new AdView(MainActivity.this);
                        binding.layoutAd.addView(adView);
                        adView.setAdSize(AdSize.BANNER);
                        adView.setAdUnitId(setting.getBanner_id());
                        AdRequest adRequest = new AdRequest.Builder().build();
                        adView.loadAd(adRequest);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Setting> call, @NonNull Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
    }
}