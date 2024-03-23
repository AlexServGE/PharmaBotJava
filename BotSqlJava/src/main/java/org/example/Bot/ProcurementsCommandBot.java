package org.example.Bot;

import org.example.Bot.Commands.CallbackHandlers.*;
import org.example.Bot.Commands.HelpCommand;
import org.example.Bot.Commands.ShowTendersSelMedicineCommand;
import org.example.Bot.Commands.StartCommand;
import org.example.Bot.Emoji.Emoji;
import org.example.Bot.Logging.BotLogger;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ProcurementsCommandBot extends TelegramLongPollingCommandBot {

  public static final String LOGTAG = "COMMANDSHANDLER";
  public static CommandsStructure com;

  //Commands processed
  public ProcurementsCommandBot(String botToken) {
    super(botToken);
    register(new StartCommand(this));
    register(new HelpCommand(this));
    register(new ShowTendersSelMedicineCommand());

    registerDefaultAction((absSender, message) -> {
      SendMessage commandUnknownMessage = new SendMessage();
      commandUnknownMessage.setChatId(message.getChatId());
      commandUnknownMessage.setText("The command '" + message.getText() + "' is not known by this bot. Here comes some help " + Emoji.AMBULANCE);
      try {
        absSender.execute(commandUnknownMessage);
      } catch (TelegramApiException e) {
        BotLogger.error(LOGTAG, e);
      }
    });
  }

  //Messages processed
  @Override
  public void processNonCommandUpdate(Update update) {
    if (update.hasCallbackQuery()) {
      handleCallback(update.getCallbackQuery());
    } else if (update.hasMessage()) {
      handleMessage(update.getMessage(), update);
    }
  }

  //Raw messages processed
  private void handleMessage(Message message, Update update) {
    //Получили текст (а не картинку итп)
//        if (update.getMessage().hasText()) {
//            Message userMessage = update.getMessage();
//            switch (userMessage) {
//                case :
//            }
//
//            SendMessage botMessage = new SendMessage();
//            botMessage.setChatId(userMessage.getChatId());
//            botMessage.setText("Hey heres your message:\n" + userMessage.getText());
//
//            try {
//                execute(echoMessage);
//            } catch (TelegramApiException e) {
//                BotLogger.error(LOGTAG, e);
//            }
//        }

  }

  //Callbackdata processed
  private void handleCallback(CallbackQuery callbackQuery) {
    // Set variables

    String callData = callbackQuery.getData();
    long message_id = callbackQuery.getMessage().getMessageId();
    long chat_id = callbackQuery.getMessage().getChatId();

    try {
      com = CommandsStructure.getCommandsStructureByTag(callData);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    switch (com) {
      case SHOWTENDERS_CONTRAST_SELREGION,
              SHOWTENDERS_ANTIBAKTERIAL_SELREGION,
              SHOWTENDERS_ANTIMICROBE_SELREGION:
        ShowTendersSelMedicineSelRegion.showTendersSelMedicineSelRegion(callData, message_id, chat_id, this);
        break;
      case SHOWTENDERS_CONTRAST_Central,
              SHOWTENDERS_CONTRAST_North_West,
              SHOWTENDERS_CONTRAST_South,
              SHOWTENDERS_CONTRAST_Privolge,
              SHOWTENDERS_CONTRAST_Ural,
              SHOWTENDERS_CONTRAST_Siberian,
              SHOWTENDERS_CONTRAST_Far_East,
              SHOWTENDERS_CONTRAST_North_Caucasus,
              SHOWTENDERS_ANTIBAKTERIAL_Central,
              SHOWTENDERS_ANTIBAKTERIAL_North_West,
              SHOWTENDERS_ANTIBAKTERIAL_South,
              SHOWTENDERS_ANTIBAKTERIAL_Privolge,
              SHOWTENDERS_ANTIBAKTERIAL_Ural,
              SHOWTENDERS_ANTIBAKTERIAL_Siberian,
              SHOWTENDERS_ANTIBAKTERIAL_Far_East,
              SHOWTENDERS_ANTIBAKTERIAL_North_Caucasus,
              SHOWTENDERS_ANTIMICROBE_Central,
              SHOWTENDERS_ANTIMICROBE_North_West,
              SHOWTENDERS_ANTIMICROBE_South,
              SHOWTENDERS_ANTIMICROBE_Privolge,
              SHOWTENDERS_ANTIMICROBE_Ural,
              SHOWTENDERS_ANTIMICROBE_Siberian,
              SHOWTENDERS_ANTIMICROBE_Far_East,
              SHOWTENDERS_ANTIMICROBE_North_Caucasus:
        ShowTendersSelMedicineSelRegionOutput.showTendersSelMedicineSelRegionOutput(callData, message_id, chat_id, this);
        break;
    }
  }


  @Override
  public String getBotUsername() {
    return BotConfig.BOTUSERNAME;
  }

}