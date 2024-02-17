package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {

        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new MyBot());
    }
}
// khrystyianyn_crypto_bot
// 6777100977:AAHUCtyDurbg7p_waaGQkzDD5BrpTAEstf4