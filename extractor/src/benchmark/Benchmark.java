package benchmark;

import java.io.File;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Benchmark
{

  public static void main(String args[])
  {
    File rep_test = null;
    File rep_gold_std = null;
    if (args.length > 2)
      {
        rep_test = new File(args[0]);
        rep_gold_std = new File(args[1]);
      }

    if (rep_test.isDirectory())
      {
        File[] list = rep_test.listFiles();
        if (list != null)
          {
            for (File f : list)
              calculateFMesures(f,
                  new File(rep_gold_std.getAbsolutePath() + f.getName()));

          }
        else
          System.err.println("Erreur lors de la lecture du répertoire.");
      }
    else
      System.err.println("Please, specify a directory !");
  }

  private static void calculateFMesures(File mail, File annoted_mail)
  {
    // TODO Auto-generated method stub

  }

  private static LinkedList<String> searchDeadlineDateByMarkup(String text)
  {
    LinkedList<String> list = new LinkedList<String>();

    Pattern p = Pattern.compile("<deadline>(.*)</deadline>",
        Pattern.CASE_INSENSITIVE);
    Matcher matcher = p.matcher(text);

    while (matcher.find())
      list.add(matcher.group(2));

    return list;
  }
}
