//package com.camellia.contactweather.webservice;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.TextView;
//
//import com.camellia.contactweather.R;
//import com.camellia.contactweather.webservice.model.DataModel;
//
//import java.util.Locale;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MainActivity extends AppCompatActivity {
//
//    private TextView textViewResult;
//    public static String lat = "35";
//    public static String lon = "52";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_webservice);
//
//        textViewResult = findViewById(R.id.myTextView);
//
//        Call call = ApiManager.getInstance().getCurrentWeather(lat, lon);
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) {
//
//                if (response.code() == 200) {
//                    DataModel weatherResponse = (DataModel) response.body();
//
//                    String stringBuilder =
//                            "Country: " + weatherResponse.sys.getCountry() +
//                                    "\n" +
//                                    "City: " + weatherResponse.getName() +
//                                    "\n" +
//                                    "Temperature: " + String.format(Locale.getDefault(), "%.1f", weatherResponse.main.getTemperature() - 273.5) +
//                                    "\n" +
//                                    "Temperature(Min): " + String.format(Locale.getDefault(), "%.1f", weatherResponse.main.getTemp_min() - 273.5) +
//                                    "\n" +
//                                    "Temperature(Max): " + String.format(Locale.getDefault(), "%.1f", weatherResponse.main.getTemp_max() - 273.5) +
//                                    "\n" +
//                                    "Humidity: " + weatherResponse.main.getHumidity() +
//                                    "\n" +
//                                    "Pressure: " + weatherResponse.main.getPressure();
//
//                    textViewResult.append(stringBuilder);
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
//                textViewResult.setText(t.getMessage());
//            }
//        });
//
//    }
//}
