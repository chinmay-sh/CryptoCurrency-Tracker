package nebula.dark.cryptocurrencytracker;

import android.graphics.Bitmap;
import android.util.Log;

public class CryptoItem {

    private String coinTicker, imgURL, price, changePercent, marketCap;

    public CryptoItem(String ticker, String price, String percent, String imgURL, String cap) {
        this.coinTicker = ticker;
        this.changePercent = percent;
        this.price = price;
        this.imgURL = imgURL;
        this.marketCap = cap;
    }

    public String getPrice() {
        return price;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getCoinTicker() {
        return coinTicker;
    }

    public String getMarketCap() {
        return marketCap;
    }
}
