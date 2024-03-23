package org.example.Bot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Тэги соответствуют сценариям.
 * Сценарии состоят из номеров кнопок.
 */

public enum CommandsStructure {
  HELPCOMMAND("1"),
  SHOWTENDERS_SELMEDICINE("2"),
  SHOWTENDERS_CONTRAST_SELREGION("21"),
  SHOWTENDERS_ANTIBAKTERIAL_SELREGION("22"),
  SHOWTENDERS_ANTIMICROBE_SELREGION("23"),

  SHOWTENDERS_CONTRAST_Central("211"),
  SHOWTENDERS_CONTRAST_North_West("212"),
  SHOWTENDERS_CONTRAST_South("213"),
  SHOWTENDERS_CONTRAST_Privolge("214"),
  SHOWTENDERS_CONTRAST_Ural("215"),
  SHOWTENDERS_CONTRAST_Siberian("216"),
  SHOWTENDERS_CONTRAST_Far_East("217"),
  SHOWTENDERS_CONTRAST_North_Caucasus("218"),

  SHOWTENDERS_ANTIBAKTERIAL_Central("221"),
  SHOWTENDERS_ANTIBAKTERIAL_North_West("222"),
  SHOWTENDERS_ANTIBAKTERIAL_South("223"),
  SHOWTENDERS_ANTIBAKTERIAL_Privolge("224"),
  SHOWTENDERS_ANTIBAKTERIAL_Ural("225"),
  SHOWTENDERS_ANTIBAKTERIAL_Siberian("226"),
  SHOWTENDERS_ANTIBAKTERIAL_Far_East("227"),
  SHOWTENDERS_ANTIBAKTERIAL_North_Caucasus("228"),

  SHOWTENDERS_ANTIMICROBE_Central("231"),
  SHOWTENDERS_ANTIMICROBE_North_West("232"),
  SHOWTENDERS_ANTIMICROBE_South("233"),
  SHOWTENDERS_ANTIMICROBE_Privolge("234"),
  SHOWTENDERS_ANTIMICROBE_Ural("235"),
  SHOWTENDERS_ANTIMICROBE_Siberian("236"),
  SHOWTENDERS_ANTIMICROBE_Far_East("237"),
  SHOWTENDERS_ANTIMICROBE_North_Caucasus("238");


  public final String tag;

  CommandsStructure(String tag) {
    this.tag = tag;
  }

  public String getTag() {
    return tag;
  }

  /**
   * Можно упростить метод getfilter или вообще без него обойтись.
   * enum экземпляры SHOWTENDERS_CONTRAST_Central("211") сделать с фильтрами SHOWTENDERS_CONTRAST_Central("211","фильтр1","фильтр2")
   * фильтр1, фильтр2 можно представить как enum более высокого порядка.
   * В таком случае необходимо будет внести изменения в switch ProcurementCommandBot
   * А также ShowTendersSelMedicineSelRegionOuput getfilters переделать/заменить на другой метод
   * Можно сделать метод getFilter через relfection API для автоматизации включения и удаления новых фильтров
   * @return
   */
  public List<String> getFilter() {
    List<String> userFilters = new ArrayList<>();
    char[] charArray = this.tag.toCharArray();
    for (int i = 1; i < charArray.length; i++) { //начинаем с i = 1, тк фильтры начинаются со второй цифры в (211,..)
      if (i == 1) { //Категория препаратов
        if (charArray[i] == '1') {
          userFilters.add("21.20.23.112: Вещества контрастные");
        } else if (charArray[i] == '2') {
          userFilters.add("21.20.10.190: Препараты противомикробные для системного использования");
        } else if (charArray[i] == '3') {
          userFilters.add("21.20.10.191: Препараты антибактериальные для системного использования");
        }
      } else if (i == 2) { //Федеральный округ
        if (charArray[i] == '1') {
          userFilters.add("Центральный");
        } else if (charArray[i] == '2') {
          userFilters.add("Северо-Западный");
        } else if (charArray[i] == '3') {
          userFilters.add("Южный");
        } else if (charArray[i] == '4') {
          userFilters.add("Приволжский");
        } else if (charArray[i] == '5') {
          userFilters.add("Уральский");
        } else if (charArray[i] == '6') {
          userFilters.add("Сибирский");
        } else if (charArray[i] == '7') {
          userFilters.add("Дальневосточный");
        } else if (charArray[i] == '8') {
          userFilters.add("Северо-Кавказский");
        }
      }
    }
    return userFilters;
  }

  public static CommandsStructure getCommandsStructureByTag(String tag) throws NoSuchMethodException {
    CommandsStructure com;
    Class<?> clazz = CommandsStructure.class;
    Method getTagMethod = clazz.getDeclaredMethod("getTag");
    List<Object> enumConstants = List.of(clazz.getEnumConstants());

    List<CommandsStructure> enumConstantsList = enumConstants.stream()
            .map(enumConstant -> (CommandsStructure) enumConstant)
            .toList();

    com = enumConstantsList.stream().filter(enumConstant -> {
      try {
        return tag.equals(getTagMethod.invoke(enumConstant));
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      } catch (InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    }).findAny().orElseThrow();
    return com;
  }

}
