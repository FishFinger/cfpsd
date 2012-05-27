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
  /*public static void main(String[] args)
  {

    System.out
        .println(searchDate("i 12-12-201423434 12 01 2004 est 12/01/04a si 12-01-2004 ua12te"));

  }*/

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
    LinkedList<DateWithPosition> list = new LinkedList<DateWithPosition>();

    //String regex = DatePattern.getNLPattern(new Locale("en"));
    //System.out.println(regex);
    //Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Pattern p = DatePattern.getSuperPattern();
    Matcher matcher = p.matcher(text);

    while (matcher.find())
      list.add(new DateWithPosition(matcher.start(), matcher.group(1)));

    return list;
  }
  
  
  // ***************************************************************************
  // TEST METHODS
  // ***************************************************************************

  
  public static void main(String args[])
  {    
    LinkedList<DateWithPosition> list;
    
    list = searchDate("Lundi 3 Janvier 04");
    System.out.println(list.size());
    
    for(DateWithPosition date : list)
      System.out.println(date);
    
    System.out.println("0/" + Cli.searchDate("15 April 2005").get(0).getPosition());
    System.out.println("1/" + Cli.searchDate(" 15 April 2005").get(0).getPosition());
    System.out.println("2/" + Cli.searchDate("  15 April 2005").get(0).getPosition());
    System.out.println("3/" + Cli.searchDate("   15 April 2005").get(0).getPosition());
    System.out.println("4/" + Cli.searchDate("    15 April 2005").get(0).getPosition());
    System.out.println("5/" + Cli.searchDate("     15 April 2005").get(0).getPosition());
    System.out.println("8/" + Cli.searchDate("Hi guy: 15 April 2005").get(0)
        .getPosition());
    System.out.println("12/" + Cli.searchDate("1 et 2 et 3 15 April 2005").get(0)
        .getPosition()); 
    
    System.out.println("12/" + Cli.searchDate("Salut mec : 15 Avril 2005").get(0)
        .getPosition());
    System.out.println("12/" + Cli.searchDate("1 et 2 et 3 15 Avril 2005").get(0)
        .getPosition());
    
   

  }
  

}
