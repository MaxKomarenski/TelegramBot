import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;

public class MyBot extends TelegramLongPollingBot {
    private HashMap<String, Action> command = new HashMap<String, Action>();
    MyBot(){
        addToHashMap();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message_text;
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            if(command.containsKey(update.getMessage().getText())) {
                message_text = command.get(update.getMessage().getText()).returnerOfData();
            }else {
                message_text = "We haven`t such command you fucking turd.";
            }


            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "ICANTTHINKUPANAMEFOR_bot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "491701730:AAFC3DMqQYbjYI5vWeqCH7jJGoFIbzjSNRY";
    }

    private void addToHashMap(){
        command.put("weather", new Weather());
    }

}