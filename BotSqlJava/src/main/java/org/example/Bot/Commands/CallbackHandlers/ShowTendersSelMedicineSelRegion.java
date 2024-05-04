package org.example.Bot.Commands.CallbackHandlers;

import org.example.Bot.CommandsStructure;
import org.example.Bot.Logging.BotLogger;
import org.example.Bot.ProcurementsCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShowTendersSelMedicineSelRegion {
    public static final String LOGTAG = "ShowTendersSelMedicineSelRegion";
    public static CommandsStructure com;

    public static void showTendersSelMedicineSelRegion(String callData, long message_id, long chat_id, ProcurementsCommandBot bot) {
        try {
            com = CommandsStructure.getCommandsStructureByTag(callData);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Выберите федеральный округ, по которому Вы хотели бы получить данные.");

        SendMessage answer = new SendMessage();

        InlineKeyboardMarkup markupInline = prepareKeyBoardCategories();

        answer.setReplyMarkup(markupInline);
        answer.setChatId(chat_id);
        answer.enableHtml(true);
        answer.setText(messageBuilder.toString());

        try {
            bot.execute(answer);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    private static InlineKeyboardMarkup prepareKeyBoardCategories() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<String> rowsWithButtons = Arrays.asList(
                "1. Центральный",
                "2. Северо-Западный",
                "3. Южный",
                "4. Приволжский",
                "5. Уральский",
                "6. Сибирский",
                "7. Дальневосточный",
                "8. Северо-Кавказский");
        int i = 1;
        for (String row : rowsWithButtons) {
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            rowInline.add(prepareKeyButtonCategories(row, com.getTag() + i));
            rowsInline.add(rowInline);
            i++;
        }

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    private static InlineKeyboardButton prepareKeyButtonCategories(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}

