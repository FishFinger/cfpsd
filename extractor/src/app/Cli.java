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

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    System.out.println(Core.searchDeadlineDate(Misc.removeHeader(Misc.read(System.in))));
  }

  private static String filter(String string)
  {
    return string.trim();
  }
}
