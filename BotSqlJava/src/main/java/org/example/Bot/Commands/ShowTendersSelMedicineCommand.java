package org.example.Bot.Commands;

import org.example.Bot.CommandsStructure;
import org.example.Bot.Logging.BotLogger;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.*;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ShowTendersSelMedicineCommand extends BotCommand {

    public static final String LOGTAG = "SHOWTENDERSCOMMAND";

    public ShowTendersSelMedicineCommand() {
        super("showTenders", "Получить информацию о закупках за последние два дня.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Выберите категорию лекарственных препаратов, по которой хотели бы получить информацию (будут показаны публикации, вышедшие вчера и позавчера):");

        SendMessage answer = new SendMessage();


        InlineKeyboardMarkup markupInline = prepareKeyBoardCategories();

        answer.setReplyMarkup(markupInline);
        answer.setChatId(chat.getId().toString());
        answer.enableHtml(true);
        answer.setText(messageBuilder.toString());


        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    private InlineKeyboardMarkup prepareKeyBoardCategories() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        List<String> rowsWithButtons = Arrays.asList(
                "1. 21.20.23.112:Контрасты",
                "2. 21.20.10.190:Антибактериальные",
                "3. 21.20.10.191:Противомикробные"
        );


        int i = 1;
        for (String row : rowsWithButtons) {
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            String callData = CommandsStructure.SHOWTENDERS_SELMEDICINE.getTag() + i;
            rowInline.add(prepareKeyButtonCategories(row, callData));
            rowsInline.add(rowInline);
            i++;
        }

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    private InlineKeyboardButton prepareKeyButtonCategories(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}


