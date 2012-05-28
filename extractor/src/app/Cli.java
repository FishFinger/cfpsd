package app;

import static org.junit.Assert.assertEquals;

import java.text.DateFormatSymbols;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cli
{

  private static final float THRESHOLD = 25.f;

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    String text = read();
    System.out.println(text);

    LinkedList<WeightedDate> list_date = new LinkedList<WeightedDate>();
    LinkedList<Integer> list_keyword_pos = searchKeyWords(text);
    LinkedList<DateWithPosition> list_date_pos = searchDate(text);

    System.out.println(list_date_pos);
    
    TreeMap<Float, String> weighted_dates = performGrade(list_date_pos,
        list_keyword_pos, text);

    System.out.println(weighted_dates);

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

  public static TreeMap<Float, String> performGrade(
      LinkedList<DateWithPosition> dates, LinkedList<Integer> keywords,
      String text)
  {

    TreeMap<Float, String> graded = new TreeMap<Float, String>();
    for (DateWithPosition date : dates)
      graded.put(getGrade(date, keywords, text), date.getDate());

    return graded;
  }

  private static Float getGrade(DateWithPosition date,
      LinkedList<Integer> keywords, String text)
  {
    try
      {
        int min_dist = Integer.MAX_VALUE;
        int dist;

        for (Integer keyword_position : keywords)
          {
            if (keyword_position < date.getPosition())
              dist = distance(text.substring(keyword_position,
                  date.getPosition()));
            else
              dist = distance(text.substring(date.getPosition(),
                  keyword_position));
            
            if(min_dist > dist)
              min_dist = dist;
          }

        float grade = (float) (1.f / Math.sqrt((float) min_dist / THRESHOLD));
        if (grade > 1.f)
          grade = 1.f;

        return grade;
      }
    catch (Exception e)
      {
        return 0.f;
      }
  }

}
