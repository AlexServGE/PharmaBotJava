package org.example.Bot;

import org.example.Bot.Logging.BotLogger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
      BotLogger.error(String.format("%s - Отсутствует файл BotToken.txt. " +
              "Его необходимо создать в корневой папке проекта и внести токен.", LOGTAG), e);
    } catch (RuntimeException e) {
      BotLogger.error(String.format("%s - В файле BotToken.txt отсутствует токен. Необходимо внести токен.", LOGTAG), e);
    }
    return botToken;
  }
}