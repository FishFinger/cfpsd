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

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    String text = read();
    System.out.println(text);

    LinkedList<WeightedDate> list_date = new LinkedList<WeightedDate>();
    LinkedList<Integer> list_keyword_pos = searchKeyWords(text);
    LinkedList<DateWithPosition> list = searchDate(text);
    for (DateWithPosition date : list)
      list_date.add(new WeightedDate(getTestDistance(date.getPosition(),
          list_keyword_pos,  text), date.getDate()));

    for (WeightedDate date : list_date)
      System.out.println(date.getWeight() + " - " + date.getDate());

  }

  private static int getDistance(int date_positon,
      LinkedList<Integer> list_keyword_pos)
  {
    int min = Integer.MAX_VALUE;
    for (int i : list_keyword_pos)
      if (Math.abs(date_positon - i) < min)
        min = Math.abs(date_positon - i);

    return min;
  }
  
  /*
   * ' ' vaut 0
   * un caractère vaut 1
   * : vaut -3
   * \n vaut 3
   */
  private static int getTestDistance(int date_positon,
      LinkedList<Integer> list_keyword_pos, String text)
  {
    int min = Integer.MAX_VALUE;
    int dist;
    for (int i : list_keyword_pos)
      {
      if(i < date_positon)
          dist = distance(text.substring(i, date_positon));
      else
        dist = distance(text.substring(date_positon, i));
      
        if (dist < min)
          min = dist;
      }

    return min;
  }
  
  private static int distance(String text)
  {
    char c;
    int val = 0;
    for(int i=0; i<text.length(); ++i)
      {
        c = text.charAt(i);
        if(c == '\n')
          val += 3;
        else if(c == ':')
          val -= 3;
        else if(!Character.isWhitespace(c))
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
    System.out.println(text);
    LinkedList<Integer> list = new LinkedList<Integer>();

    Pattern p = KeyWordPattern.getPattern();
    Matcher matcher = p.matcher(text);

    while (matcher.find())
      {
        System.out.println(matcher.group(1));
        list.add(matcher.start(1));
      }

    System.out.println();

    return list;
  }

  public static TreeMap<Float, DateWithPosition> performGrade(
      LinkedList<DateWithPosition> dates, LinkedList<Integer> keywords)
  {

    TreeMap<Float, DateWithPosition> graded = new TreeMap<>();
    for (DateWithPosition date : dates)
      graded.put(getGrade(date, keywords), date);

    return graded;
  }

  private static Float getGrade(DateWithPosition date,
      LinkedList<Integer> keywords)
  {
    try
      {
        int min_dist = keywords.get(0);
        int dist;

        for (Integer keyword_position : keywords)
          {
            dist = Math.abs(date.getPosition() - keyword_position);
            if (min_dist > dist)
              min_dist = dist;
          }

        float grade = (float) 1 / (float) Math.sqrt((float) min_dist / 30.0);
        if (grade > 1.0)
          grade = (float) 1.0;

        return grade;
      }
    catch (Exception e)
      {
        return (float) 0;
      }
  }


}
