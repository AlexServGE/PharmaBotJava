package org.example.Bot;

import com.sun.security.jgss.GSSUtil;
import org.example.Bot.Logging.BotLogger;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.PatternSyntaxException;

public class BotConfig {
  private static final String LOGTAG = "BOTCONFIG";
  public final static String BOTTOKEN = getBotTokenFromFile();
  public final static String BOTUSERNAME = "...";

  public static String getBotTokenFromFile() {
    String botToken = null;
    Path botTokenPath = Paths.get("./BotToken.txt");
    try (InputStream is = Files.newInputStream(botTokenPath)) {
      BufferedInputStream bIs = new BufferedInputStream(is);
      botToken = new String(bIs.readAllBytes());
      if (botToken.isEmpty()) {
        throw new RuntimeException();
      }
    } catch (IOException e) {
      BotLogger.error(String.format("%s - Отсутствует файл BotToken.txt. Необходимо его создать и внести токен.", LOGTAG), e);
    } catch (RuntimeException e) {
      BotLogger.error(String.format("%s - В файле BotToken.txt отсутствует токен. Необходимо внести токен.", LOGTAG), e);
    }
    return botToken;
  }
}