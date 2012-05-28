package app;

import java.util.regex.Pattern;

public class KeyWordPattern
{
  private enum keywords
  {
    SUBMITTED, SUBMIT, SUBMISSION, SOUMISSION, SOUMETTRE, DEADLINE
  }

  static Pattern pattern = null;

  public static Pattern getPattern()
  {
    if (pattern == null)
      {
        String string_pattern = "(PAPER SUBMISSION|";
        for (int i = 0; i < keywords.values().length; ++i)
          {
            if (i > 0)
              string_pattern += "|";

            string_pattern += keywords.values()[i].toString();

          }

        string_pattern += ")";
        pattern = Pattern.compile(string_pattern, Pattern.CASE_INSENSITIVE);
      }

    return pattern;
  }

}
