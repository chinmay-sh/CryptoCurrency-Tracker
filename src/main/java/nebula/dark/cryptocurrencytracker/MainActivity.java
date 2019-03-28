package nebula.dark.cryptocurrencytracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private final String NUMBER_OF_COINS = "20";

    private final String BASE_URL = "https://chasing-coins.com/api/v1/top-coins/" + NUMBER_OF_COINS;

    private final String LOGO_URL = "https://chasing-coins.com/api/v1/std/logo/";

    ArrayList<CryptoItem> cryptoCoinsList;
    ListView list_view;
    CryptoItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cryptoCoinsList = new ArrayList<>();
        list_view = findViewById(R.id.list);

        apiHandler(BASE_URL);

        ImageView refresh_button = findViewById(R.id.refreshView);
        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                Toast.makeText(MainActivity.this, "Refreshing", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void apiHandler(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        adapter = new CryptoItemAdapter(this, cryptoCoinsList);

        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    for (int i = 1; i <= 20; i++) {
                        JSONObject currentObject = response.getJSONObject(String.valueOf(i));
                        String currentPrice = currentObject.getString("price");
                        String currentSymbol = currentObject.getString("symbol");
                        String currentCap = currentObject.getString("cap");
                        String imgurl = LOGO_URL + currentSymbol;
                        String hourChange = currentObject.getJSONObject("change").getString("hour");
                        CryptoItem currentItem = new CryptoItem(currentSymbol, currentPrice, hourChange, imgurl, currentCap);
                        cryptoCoinsList.add(currentItem);
                        Log.d("CryptoApi", "API REQUEST COMPLETE, LIST SIZE: " + cryptoCoinsList.size());

                    }
                    list_view.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("CryptoTracker", "Request Failed");
            }
        });

    }
}
