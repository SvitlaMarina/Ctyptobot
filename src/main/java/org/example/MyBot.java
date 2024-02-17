package org.example;

import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {
    public MyBot(){super("6777100977:AAHUCtyDurbg7p_waaGQkzDD5BrpTAEstf4");}

    @Override
    public void onUpdateReceived(Update update) {
       var chatId=update.getMessage().getChatId();
       String text = update.getMessage().getText();
       try {

       String[]words=text.split(",");
       String str="";

       if(text.equals("/start")){
           sendMessage(chatId,"HALLO");
       } else if (text.equals("/all")){
           sendCript(chatId, "btc");
           sendCript(chatId, "eth");
           sendCript(chatId, "doge");
       } else {
           for (int i = 0; i < words.length; i++) {
               sendCript(chatId,words[i]);
           }
       }
     } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    void sendCript(long chatId, String name) throws Exception{
        if (name.equals("btc")){
            sendPicture(chatId, "bitcoin.png");
            sendPrice(chatId, "BTC");
        }else if(name.equals("eth")){
            sendPicture(chatId, "ethereum.png");
            sendPrice(chatId, "ETH");
        }else if(name.equals("doge")){
            sendPicture(chatId, "dogecoin.png");
            sendPrice(chatId, "DOGE");
        }else sendMessage(chatId, "Unknown command!");
    }
    void sendPicture(long chatId, String name) throws Exception {
        var photo = getClass().getClassLoader().getResourceAsStream(name);
        var message = new SendPhoto();
        message.setChatId(chatId);
        message.setPhoto(new InputFile(photo, name));
        execute(message);
    }
    void sendPrice(long chatId, String name) throws Exception {
        var price = CryptoPrice.spotPrice(name);
        sendMessage(chatId, name + " price: " + price.getAmount().doubleValue());
    }


    void sendMessage(long chatId, String text) throws Exception {
        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        execute(message);
    }

    @Override
    public String getBotUsername() {
        return "khrystyianyn_crypto_bot";
    }
}
