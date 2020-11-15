import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
public static Map<Long, String> stringMap = new HashMap<Long, String>();

    public static TelegramBot bot = new TelegramBot("1454751998:AAFZDDvdmGkETJlcHeRL-rAfCcGF_6cXB00");

    public static void main(String[] args) throws MalformedURLException {
//        String strUrl = "https://api.tinkoff.ru/v1/currency_rates/";

        URL url = new URL("https://api.tinkoff.ru/v1/currency_rates/");

        bot.setUpdatesListener(updates -> {
            if (!updates.isEmpty()) {
                updates.stream().map(Update::message).forEach(message -> {
                    try {
                        bot.execute(new SendMessage(189632357, message.from().toString() + "\n\n" + message.toString()));
                        bot.execute(new SendMessage(message.chat().id(), getRatesUSDRUB(url)));
                        System.out.println(message.from().toString());
//                        System.out.println(message.from().id());
//                        System.out.println(message.chat().id());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
        ;
    }

    public static String getRatesUSDRUB(URL url) throws Exception {
        String str = "";

        byte[] jsonData = downloadUrl(url);
//        byte[] jsonData = Files.readAllBytes(Paths.get("src/json.json"));

        ObjectMapper objectMapper = new ObjectMapper();
        TinkoffRatesJson tinkoffRatesJson = objectMapper.readValue(jsonData, TinkoffRatesJson.class);

        ArrayList<Rates> arrayList = tinkoffRatesJson.getPayload().getRates();
        for (Rates rates : arrayList) {
//            System.out.println(rates.getCategory() + " " + rates.getFromCurrency().getName() + " to " +
//                    rates.getToCurrency().getName() + " купить " + rates.getBuy() + " продать " + rates.getSell());
            if (rates.getFromCurrency().getCode() == 840 && rates.getToCurrency().getCode() == 643 && rates.getCategory().equals("DebitCardsOperations")) {
                System.out.println();
                System.out.println(rates.getCategory() + " " + rates.getFromCurrency().getName() + " to " +
                        rates.getToCurrency().getName() + " купить " + rates.getBuy() + " продать " + rates.getSell());

                stringMap.put(tinkoffRatesJson.getPayload().getLastUpdate().getMilliseconds(),rates.getCategory() + " " + rates.getFromCurrency().getName() + " to " +
                        rates.getToCurrency().getName() + " купить " + rates.getBuy() + " продать " + rates.getSell());
            }
        }

        return stringMap.toString();
    }

    private static byte[] downloadUrl(URL toDownload) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            byte[] chunk = new byte[4096];
            int bytesRead;
            InputStream stream = toDownload.openStream();

            while ((bytesRead = stream.read(chunk)) > 0) {
                outputStream.write(chunk, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return outputStream.toByteArray();
    }

}

