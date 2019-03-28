package nebula.dark.cryptocurrencytracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CryptoItemAdapter extends ArrayAdapter<CryptoItem> {
    public CryptoItemAdapter(@NonNull Context context, @NonNull ArrayList<CryptoItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CryptoItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        TextView ticker = convertView.findViewById(R.id.ticker_view);
        TextView price = convertView.findViewById(R.id.inr_view);
        TextView changePercent = convertView.findViewById(R.id.change_view_percent);
        ImageView cryptoImg = convertView.findViewById(R.id.crypto_img_view);


        String tempPrice = "$" + String.valueOf(item.getPrice());

        String tempChangePercent = item.getChangePercent() + "%";
        changePercent.setText(tempChangePercent);
        price.setText(tempPrice);
        ticker.setText(item.getCoinTicker());

        Picasso.get().load(item.getImgURL()).into(cryptoImg);

        if (!tempChangePercent.contains("-")) {
            changePercent.setBackgroundResource(R.color.greenBack);
        } else {
            changePercent.setBackgroundResource(R.color.redBack);
        }

        return convertView;
    }
}
