package benchmark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.Core;
import app.WeightedDate;

import util.Misc;

public class Benchmark
{

  private static int true_positif = 0;
  private static int false_positif = 0;
  private static int true_negatif = 0;
  private static int false_negatif = 0;

  public static void main(String args[]) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException
  {
    File rep_test = null;
    File rep_gold_std = null;
    if (args.length == 2)
      {
        rep_test = new File(args[0]);
        rep_gold_std = new File(args[1]);

        if (rep_test.isDirectory())
          {
            File[] list = rep_test.listFiles();
            System.out.println(list.length);

            int i = -1;
            short percent = 0, old_percent;
            if (list != null)
              {
                for (File f : list)
                  {
                    Misc.printPercent(++i, list.length);

                    calculateFMesures(f,
                        new File(rep_gold_std.getAbsolutePath() + "/" + f.getName()));
                  }
                
                
                System.out.println("Report:");
                System.out.println("False negative = " + false_negatif);
                System.out.println("False postive = " + false_positif);

               
              }
            else
              System.err.println("Erreur lors de la lecture du répertoire.");
          }

        return;
      }

    System.err.println("Please, specify two directory !");
  }

  private static void calculateFMesures(File mail, File annoted_mail) throws FileNotFoundException, UnsupportedEncodingException
  {
    float threshold = 1.f;
    
    LinkedList<WeightedDate> founded_dates = Core.searchDeadlineDate(Misc
        .read(mail));

    LinkedList<String> ref_dates = searchDeadlineDateByMarkup(Misc
        .read(annoted_mail));
    
    for(WeightedDate date : founded_dates)
      if(date.getWeight() >= threshold)
        if(!ref_dates.contains(date.getDate()))
          ++false_positif;
    
    boolean is_found; 
    for(String ref_date : ref_dates)
      {
        is_found = false;
        
        for(WeightedDate f_date : founded_dates)      
          if(f_date.getWeight() >= threshold && f_date.getDate().equals(ref_date))
            {
              is_found = true;
              break;
            }
        
        if(!is_found)
          ++false_negatif;
      }
    

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
