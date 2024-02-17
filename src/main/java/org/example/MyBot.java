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
       var message = new SendMessage();
       message.setChatId(chatId);
       var priceBtc= CryptoPrice.spotPrice("BTC");
       var priceEth= CryptoPrice.spotPrice("ETH");
       var priceDoge= CryptoPrice.spotPrice("DOGE");

       String[]words=text.split(",");
       String str="";
       if(text.equals("/start")){
           message.setText("HALLO");
       } else if (text.equals("/all")){
           message.setText("BTC price: "+priceBtc.getAmount().doubleValue()+"\n"+
                   "ETH price: "+priceEth.getAmount().doubleValue()+"\n"+
                   "Doge price: "+priceDoge.getAmount().doubleValue());
       }else {
           int unknown=0;
           for (int i = 0; i < words.length; i++) {
               if (words[i].equals("btc")){
                   str=str+"BTC price: "+priceBtc.getAmount().doubleValue()+"\n";
               }else if(words[i].equals("eth")){
                   str=str+"ETH price: "+priceEth.getAmount().doubleValue()+"\n";
               }else if(words[i].equals("doge")){
                   str=str+"DOGE price: "+priceDoge.getAmount().doubleValue()+"\n";
               }else {unknown=1;}

           }
           if (unknown==1){message.setText("Unknown command");}
           else {message.setText(str);}
       }




       execute(message);
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    @Override
    public String getBotUsername() {
        return "khrystyianyn_crypto_bot";
    }
}
