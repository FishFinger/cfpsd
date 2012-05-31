package app;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
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

  /**
   * @param args
   * @throws UnsupportedEncodingException 
   */
  public static void main(String[] args) throws UnsupportedEncodingException
  {
    if(args.length > 0)
      {
        printUsage();
        System.exit(1);
      }
    System.out.println(Core.searchDeadlineDate(Misc.removeHeader(Misc.read(System.in))));
  }

  private static void printUsage()
  {
    System.out.println("\n" +
        "Usage :\n" +
        "  write your email on standard input\n\n" +
        "Exemple :\n" +
        "  cat mail.txt | java -jar extractor.jar\n"
        );
  }
  
  private static String filter(String string)
  {
    return string.trim();
  }
}
