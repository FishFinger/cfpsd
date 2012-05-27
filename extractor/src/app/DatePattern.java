package app;

import util.Misc;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;
import java.text.DateFormatSymbols;

public class DatePattern
{

  private static Pattern super_pattern = null;

  public static enum Style
  {
    SHORT, LONG, ALL
  }

  public static enum Field
  {
    WEEKDAYS, MONTH
  }

  public static final String NUM_DAY = "(0?[1-9]|[12][0-9]|3[01])";
  public static final String NUM_MONTH = "(0?[1-9]|1[012])";
  public static final String YEAR = "((2[0-9])?[0-9][0-9])";
  public static final String SEP = "[-/_]";
  public static final String NUMERIC_DATE = "(((" + NUM_DAY + SEP + NUM_MONTH
      + ")|("  + NUM_MONTH + SEP + NUM_DAY + "))" + SEP + YEAR + ")";

  public static Pattern getSuperPattern()
  {
    if (super_pattern == null)
      {
        String date = "(" + NUMERIC_DATE + "|";

        // Locale[] locales = DateFormatSymbols.getAvailableLocales();
        Locale[] locales = new Locale[2];
        locales[0] = new Locale("en");
        locales[1] = new Locale("fr");
        for (int i = 0; i < locales.length; ++i)
          date += getNLPattern(locales[i]) + "|";

        date = date.substring(0, date.length() - 1) + ')';

        super_pattern = Pattern.compile(date, Pattern.CASE_INSENSITIVE);
      }
    return super_pattern;
  }

  public static String getNLPattern(Locale locale)
  {

    String weekday = getNLPattern(Field.WEEKDAYS, Style.ALL, locale);
    String month = getNLPattern(Field.MONTH, Style.ALL, locale);

    String sep = " ";

    String nl_date = "((" + weekday + sep + ")?" + NUM_DAY + "(\\p{Alpha}{2})?"
        + sep + month + "(" + sep + YEAR + ")?)";

    if (locale.toLanguageTag().equals("en"))
      nl_date = "(" + nl_date + "|(" + month + "\\s{1,3}" + NUM_DAY
          + "(,\\s{1,3}" + YEAR + ")?))";

    return nl_date;
  }

  /**
   * 
   * @param field
   *          the field for the pattern
   * @param style
   *          the style use for the pattern; one of SHORT, LONG,
   *          or ALL_STYLES.
   * @param locale
   *          the locale which is used for the pattern.
   * @return a string that represent a Pattern
   */
  public static String getNLPattern(Field field, Style style, Locale locale)
  {
    if (locale == null)
      return new String();

    String pattern = "(";

    for (String val : getNLName(field, style, locale))
      pattern += val + "|";

    pattern = pattern.substring(0, pattern.length() - 1) + ')';

    return pattern;
  }

  // ***************************************************************************
  // PRIVATE METHODS
  // ***************************************************************************

  private static String[] getNLName(Field field, Style style, Locale locale)
  {
    if (field == Field.WEEKDAYS)
      {
        return getNLWeekdays(style, locale);
      }
    else if (field == Field.MONTH)
      {
        return getNLMonths(style, locale);
      }
    else
      return new String[0];
  }

  private static String[] getNLMonths(Style style, Locale locale)
  {
    DateFormatSymbols date_symbols;
    date_symbols = new DateFormatSymbols(locale);

    if (style == Style.SHORT)
      return date_symbols.getShortMonths();
    else if (style == Style.LONG)
      return date_symbols.getMonths();
    else
      {
        String[] short_name = Misc.removeEmpty(date_symbols.getShortMonths());
        String[] long_name = Misc.removeEmpty(date_symbols.getMonths());

        // other
        if (locale.toLanguageTag().equals("fr"))
          {
            String[] other = { "fév." };
            short_name = Misc.concatArrays(short_name, other);
          }

        Misc.replace(short_name, ".", "(\\.)?");
        String[] months = Misc.concatArrays(long_name, short_name);
        LinkedList<String> list = newByReplaceFrenchAccent(months);
        months = Misc.concatArrays(months,
            list.toArray(new String[list.size()]));

        return months;
      }
  }

  private static LinkedList<String> newByReplaceFrenchAccent(String[] strings)
  {
    LinkedList<String> list = new LinkedList<String>();

    list.addAll(Misc.getNewByReplace(strings, 'é', 'e'));
    list.addAll(Misc.getNewByReplace(strings, 'û', 'u'));

    return list;
  }

  private static String[] getNLWeekdays(Style style, Locale locale)
  {
    DateFormatSymbols date_symbols;
    date_symbols = DateFormatSymbols.getInstance(locale);

    if (style == Style.SHORT)
      return date_symbols.getShortWeekdays();
    else if (style == Style.LONG)
      return date_symbols.getWeekdays();
    else
      {
        String[] short_name = Misc.removeEmpty(date_symbols.getShortWeekdays());
        String[] long_name = Misc.removeEmpty(date_symbols.getWeekdays());

        Misc.replace(short_name, ".", "(\\.)?");

        return Misc.concatArrays(long_name, short_name);
      }
  }

  // ***************************************************************************
  // TEST METHODS
  // ***************************************************************************

  public static void main(String args[])
  {
    Locale[] locales = DateFormatSymbols.getAvailableLocales();
    for (Locale l : locales)
      System.out.print(l + "/");

    System.out.println();

    String pattern = getSuperPattern().toString();
    // String pattern = getNLPattern(new Locale("fr"));
    System.out.println("\n" + pattern);

    // System.out.println(Misc.countBracket(pattern));
  }

}
