package app;

import static org.junit.Assert.assertEquals;

import java.text.DateFormatSymbols;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cli
{

  /**
   * @param args
   */
  /* public static void main(String[] args)
   * {
   * 
   * System.out
   * .println(searchDate(
   * "i 12-12-201423434 12 01 2004 est 12/01/04a si 12-01-2004 ua12te"));
   * 
   * } */

  private static String read()
  {
    Scanner scan = new Scanner(System.in);
    String text = "";

    while (scan.hasNextLine())
      {
        text += scan.nextLine();
      }

    return text;
  }

  public static LinkedList<DateWithPosition> searchDate(String text)
  {
    System.out.println(text);
    LinkedList<DateWithPosition> list = new LinkedList<DateWithPosition>();

    Pattern p = DatePattern.getSuperPattern();
    Matcher matcher = p.matcher(text);

    while (matcher.find())
      {
        System.out.println(matcher.group(2));
        list.add(new DateWithPosition(matcher.start(2), matcher.group(2)));
      }

    System.out.println();

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

  // ***************************************************************************
  // TEST METHODS
  // ***************************************************************************

  public static void main(String args[])
  {

    LinkedList<DateWithPosition> date_list;

    date_list = searchDate("Lundi 3 Janvier 04");
    System.out.println(date_list.size());

    for (DateWithPosition date : date_list)
      System.out.println(date);

    System.out.println("0/"
        + Cli.searchDate("15 April 2005").get(0).getPosition());
    System.out.println("1/"
        + Cli.searchDate(" 15 April 2005").get(0).getPosition());
    System.out.println("2/"
        + Cli.searchDate("  15 April 2005").get(0).getPosition());
    System.out.println("3/"
        + Cli.searchDate("   15 April 2005").get(0).getPosition());
    System.out.println("4/"
        + Cli.searchDate("    15 April 2005").get(0).getPosition());
    System.out.println("5/"
        + Cli.searchDate("     15 April 2005").get(0).getPosition());
    System.out.println("8/"
        + Cli.searchDate("Hi guy: 15 April 2005").get(0).getPosition());
    System.out.println("12/"
        + Cli.searchDate("1 et 2 et 3 15 April 2005").get(0).getPosition());

    System.out.println("12/"
        + Cli.searchDate("Salut mec : 15 Avril 2005").get(0).getPosition());
    System.out.println("12/"
        + Cli.searchDate("1 et 2 et 3 15 Avril 2005").get(0).getPosition());
    
    System.out.println("Test Keywords");
    Cli.searchKeyWords("Bonjour, voici la date limite de soumission des papiers : ");
    Cli.searchKeyWords("Can you submit papers before this date ?!");

  }
  

}
