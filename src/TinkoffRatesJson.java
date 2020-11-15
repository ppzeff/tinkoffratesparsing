import java.util.ArrayList;

public class TinkoffRatesJson {
    private String resultCode;
    private String trackingId;
    private Payload payload;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}

class Payload {
    private LastUpdate lastUpdate;
    private ArrayList<Rates> rates;

    public LastUpdate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LastUpdate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public ArrayList<Rates> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rates> rates) {
        this.rates = rates;
    }
}


class LastUpdate {
    private long milliseconds;

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }
    @Override
    public String toString(){
        return String.valueOf(milliseconds);
    }
}

class Rates {
    private String category;
    private double buy;
    private double sell;
    private FromCurrency fromCurrency;
    private ToCurrency toCurrency;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public FromCurrency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(FromCurrency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public ToCurrency getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(ToCurrency toCurrency) {
        this.toCurrency = toCurrency;
    }
}

class FromCurrency {
    private int code;
    private String strCode;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class ToCurrency {
    private int code;
    private String strCode;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


