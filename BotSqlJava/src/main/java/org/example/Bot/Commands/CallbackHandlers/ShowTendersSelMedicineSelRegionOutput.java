package org.example.Bot.Commands.CallbackHandlers;

import org.example.Bot.CommandsStructure;
import org.example.Bot.Logging.BotLogger;
import org.example.Bot.ProcurementsCommandBot;
import org.example.Database.OrmDbManager;
import org.example.Database.Tender;
import org.example.Dates.DayInWeek;
import org.example.Dates.TodayDateAndOffsets;
import org.hibernate.Session;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ShowTendersSelMedicineSelRegionOutput {

  public static final String LOGTAG = "ShowTendersSelMedicineSelRegionOutput";
  public static CommandsStructure com;
  public static String userFilters;

  public static void showTendersSelMedicineSelRegionOutput(String callData, long message_id, long chat_id, ProcurementsCommandBot bot) {
    try {
      com = CommandsStructure.getCommandsStructureByTag(callData);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    StringBuilder messageBuilder = new StringBuilder();
    messageBuilder.append("Данные скоро будут отображены.");

    SendMessage answer = new SendMessage();
    answer.setChatId(chat_id);
    answer.enableHtml(true);
    answer.setText(messageBuilder.toString());

    try {
      bot.execute(answer);
    } catch (TelegramApiException e) {
      BotLogger.error(LOGTAG, e);
    }

    List<Tender> tenders = selectFromDb();
    for (Tender tender : tenders) {
      try {
        answer.setText(tender.getStringOutputForBot()); //TODO id тендера должен быть его номер публикации,
        //TODO подумать над более презентабельным представлением
        //TODO разместить базу в другую папку
        bot.execute(answer);
      } catch (TelegramApiException e) {
        BotLogger.error(LOGTAG, e);
      }
    }



  }

  public static List<Tender> selectFromDb() {
    OrmDbManager ormDbManager = new OrmDbManager();
    List<Tender> tenders;
    try (Session curSession = ormDbManager.getSession()) {
      TodayDateAndOffsets todayDateAndOffsets = new TodayDateAndOffsets();
      String todayOffset1 = todayDateAndOffsets.getTodayDateOffset1(); //yesterday
      String todayOffset2 = todayDateAndOffsets.getTodayDateOffset2(); //twiceyesterday
      String todayOffset3 = todayDateAndOffsets.getTodayDateOffset3(); //friday
      String todayOffset4 = todayDateAndOffsets.getTodayDateOffset4(); //friday
      int todayWeekDay = todayDateAndOffsets.getDayOfWeek();

      List<String> userFilters = com.getFilter();
      String category = userFilters.get(0);
      String federalRegion = userFilters.get(1);
      System.out.println(userFilters);

      if (todayWeekDay == DayInWeek.MONDAY.getNumber()) {
        tenders = ormDbManager.selectAllFromDBProcurements(curSession, category, federalRegion, todayOffset4, todayOffset1);
      } else if (todayWeekDay == DayInWeek.TUESDAY.getNumber()) {
        tenders = ormDbManager.selectAllFromDBProcurements(curSession, category, federalRegion, todayOffset2, todayOffset1);
      } else {
        tenders = ormDbManager.selectAllFromDBProcurements(curSession, category, federalRegion, todayOffset3, todayOffset1);
      }
    }
    return tenders;
  }

}
