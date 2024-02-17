package org.example;

import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
           sendPrice(chatId, "BTC");
           sendPrice(chatId, "ETH");
           sendPrice(chatId, "DOGE");
       } else {
           for (int i = 0; i < words.length; i++) {
               if (words[i].equals("btc")){
                   sendPrice(chatId, "BTC");
               }else if(words[i].equals("eth")){
                   sendPrice(chatId, "ETH");
               }else if(words[i].equals("doge")){
                   sendPrice(chatId, "DOGE");
               }else sendMessage(chatId, "Unknown command!");
           }
       }
     } catch (Exception e) {
            System.out.println("Error!");
        }
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
