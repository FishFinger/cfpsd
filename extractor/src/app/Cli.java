package app;

import static org.junit.Assert.assertEquals;

import java.text.DateFormatSymbols;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Misc;

public class Cli
{

  private static final float THRESHOLD = 25.f;

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    System.out.println(searchDeadlineDate(Misc.removeHeader(read())));
  }

  private static LinkedList<WeightedDate> searchDeadlineDate(String text)
  {
    LinkedList<Integer> list_keyword_pos = searchKeyWords(text);
    LinkedList<DateWithPosition> list_date_pos = searchDate(text);

    LinkedList<WeightedDate> weighted_dates = performGrade(list_date_pos,
        list_keyword_pos, text);

    return weighted_dates;
  }

  private static int distance(String text)
  {
    char c;
    int val = 0;
    for (int i = 0; i < text.length(); ++i)
      {
        c = text.charAt(i);
        if (c == '\n')
          val += 5;
        else if (c == ':')
          val -= 10;
        else if (!Character.isWhitespace(c))
          val += 1;
      }

    return val;
  }

  private static String read()
  {
    Scanner scan = new Scanner(System.in);
    String text = "";

    while (scan.hasNextLine())
      {
        text += scan.nextLine() + "\n";
      }

    return text;
  }

  private static String filter(String string)
  {
    return string.trim();
  }

  public static LinkedList<DateWithPosition> searchDate(String text)
  {
    LinkedList<DateWithPosition> list = new LinkedList<DateWithPosition>();

    Pattern p = DatePattern.getSuperPattern();
    Matcher matcher = p.matcher(text);

    while (matcher.find())
      list.add(new DateWithPosition(matcher.start(2), matcher.group(2)));

    return list;
  }

  public static LinkedList<Integer> searchKeyWords(String text)
  {
    LinkedList<Integer> list = new LinkedList<Integer>();

    Pattern p = KeyWordPattern.getPattern();
    Matcher matcher = p.matcher(text);

    while (matcher.find())
      list.add(matcher.start(1));

    return list;
  }

  public static LinkedList<WeightedDate> performGrade(
      LinkedList<DateWithPosition> dates, LinkedList<Integer> keywords,
      String text)
  {

    LinkedList<WeightedDate> graded = new LinkedList<WeightedDate>();
    for (DateWithPosition date : dates)
      graded.add(new WeightedDate(getGrade(date, keywords, text, dates), date
          .getDate()));

    return graded;
  }

  private static Float getGrade(DateWithPosition date,
      LinkedList<Integer> keywords, String text,
      LinkedList<DateWithPosition> all_dates)
  {
    try
      {
        int min_dist = Integer.MAX_VALUE;
        int dist;

        for (Integer keyword_position : keywords)
          {
            if (keyword_position < date.getPosition())
              {
                dist = distance(text.substring(keyword_position,
                    date.getPosition()));

                for (DateWithPosition other_date : all_dates)
                  if (other_date.getPosition() > keyword_position
                      && other_date.getPosition() < date.getPosition())
                    dist += 20;
              }
            else
              {
                dist = distance(text.substring(date.getPosition(),
                    keyword_position));
                for (DateWithPosition other_date : all_dates)
                  if (other_date.getPosition() < keyword_position
                      && other_date.getPosition() > date.getPosition())
                    dist += 20;
              }

            if (min_dist > dist)
              min_dist = dist;
          }

        if (min_dist <= 0)
          min_dist = 1;

        System.out.println(min_dist + "-" + date.getDate());

        float grade = (float) (1.f / Math.sqrt((float) min_dist / THRESHOLD));
        if (grade > 1.f)
          grade = 1.f;

        return grade;
      }
    catch (Exception e)
      {
        return 1.f;
      }
  }

}
