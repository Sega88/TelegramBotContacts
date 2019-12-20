import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



/**
 * Created by Sergei on 18.12.2019.
 */
public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot());

        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplayToMessageId(message.getMessegeId());
        sendMessage.setText(text);
        try{
            sendMessage(sendMessage)
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }


    public void onUpdateReceived(Update update) { //для приема сообщений (обновление)
        Message message = update.getMessage();
        if(message !=null && message.hasText()){
            switch(message.getText()){
                case "/help":
                    sendMsg(message, "Чем могу помочь?");
                            break;
                case "/settings":
                    sendMsg(message, "Что будем настраивать");
                    break;
                default:
            }
        }

    }

    public String getBotUsername() { //вернуть имя при регистрации
        return "MyWorkContactsBot";
    }

    @Override
    public String getBotToken() { //токен
        return "884917268:AAFUCIGmG-OT5_uKpcvP0Utsngmt6pt226A";
    }
}
