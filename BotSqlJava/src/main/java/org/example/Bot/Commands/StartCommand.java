package org.example.Bot.Commands;

import org.example.Bot.Emoji.Emoji;
import org.example.Bot.Logging.BotLogger;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



public class StartCommand extends BotCommand {

    public static final String LOGTAG = "STARTCOMMAND";

    private final ICommandRegistry commandRegistry;

    public StartCommand(ICommandRegistry commandRegistry) {
        super("start", "Стартовать бота.");
        this.commandRegistry = commandRegistry;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Добрый день, "+ user.getFirstName() + "!\n");
        messageBuilder.append("Вас приветствует PharmaBot. " + Emoji.INFORMATION_SOURCE + "\n");
        messageBuilder.append("Я здесь, чтобы помочь быть в курсе закупок лекарственных препаратов 44-ФЗ."+ Emoji.ROCKET+"\n");

        for (IBotCommand botCommand : commandRegistry.getRegisteredCommands()) {
            messageBuilder.append(botCommand.toString()).append("\n");
        }

        SendMessage answer = new SendMessage();
        answer.setChatId(chat.getId().toString());
        answer.enableHtml(true);
        answer.setText(messageBuilder.toString());



        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }
}
