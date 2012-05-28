package app;

import static org.junit.Assert.assertEquals;

import java.text.DateFormatSymbols;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;
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

  // ***************************************************************************
  // TEST METHODS
  // ***************************************************************************

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

    System.out.println("Test avec la chaine :");
    String chaine = "Bonjour, nous vous prions de bien vouloir soumettre vos papier avant la date du 17 avril 2012. Merci";
    TreeMap<Float, DateWithPosition> find = Cli.performGrade(
        Cli.searchDate(chaine), Cli.searchKeyWords(chaine));
    System.out.println(find.toString());

    System.out.println("Test avec la chaine :");
    chaine = "Submission date: 10 avril 2011.";
    find = Cli.performGrade(Cli.searchDate(chaine), Cli.searchKeyWords(chaine));
    System.out.println(find.toString());
    
    System.out.println("Test avec l'email 23 du corpus sample :");
    chaine = "[Apologies for multiple postings] CALL FOR PAPERS ACL 2005 WORKSHOP ON COMPUTATIONAL APPROACHES TO SEMITIC LANGUAGES University of Michigan, Ann Arbor June 29, 2005 **********Submission Deadline April 10 2005 ****************** WORKSHOP DESCRIPTION ==================== The Semitic family includes many languages and dialects spoken by a large number of native speakers (around 300 Million). However, Semitic languages are still understudied. The most prominent members of this family are Arabic and its dialects, Hebrew, Amharic, Aramaic, Maltese and Syriac. Beyond their shared ancestry which is apparent through pervasive cognate sharing, a common characteristic of these languages is the rich and productive pattern-based morphology and similar syntactic constructions. An increasing body of computational linguistics work is starting to appear for both Arabic and Hebrew. Arabic alone, as the largest member of the Semitic family, has been receiving a lot of attention lately in terms of dedicated workshops and conferences. These include, but are not limited to, the workshop on Arabic Language Resources and Evaluation (LREC 2002), a special session on Arabic processing in Traitement Automatique du Langage Naturel (TALN 2004), the Workshop on Computational Approaches to Arabic Script-based Languages (COLING 2004), and the NEMLAR Arabic Language Resources and Tools Conference in Cairo, Egypt (2004). This phenomenon has been coupled with a relative surge in resources for Arabic due to concerted efforts by the LDC and ELDA/ELRA. However, there is an apparent lag in the development of resources and tools for other Semitic languages. Often, work on individual Semitic languages, unfortunately, still tends to be done with limited awareness of ongoing research in other Semitic languages. Within the last four years, only three workshops addressed Semitic languages: an ACL 2002 Workshop on Computational Approaches to Semitic Languages and an MT Summit IX Workshop on Machine Translation for Semitic Languages in 2003, and the EAMT 2004, held in Malta, had a special session on Semitic languages. This workshop is a sequel to the ACL 2002 workshop and shares its goals of: (i) heightening awareness amongst Semitic-language researchers of shared breakthroughs and challenges, (ii) highlighting issues common to all Semitic languages as much as possible, (iii) encouraging the potential for developing coordinated approaches; and (iv) in addition, leveraging resource and tool creation for less prominent members of the Semitic language family. WORKSHOP TOPICS =============== We invite submissions of papers addressing any of the following issues: - Computational approaches to phonology, morphology, syntax, semantics and pragmatics of Semitic languages - Applications for Semitic languages such as, but not limited to, machine translation, summarization and information retrieval - Tools for processing of Semitic languages (e.g. POS taggers, parsers, etc.) - Empirical studies of unique/specific phenomena in Semitic languages - Creating computational resources for Semitic languages - Comparative computational studies of Semitic languages - Leveraging resources in other languages (Semitic or other) to create resources and tools for Semitic languages While we invite submissions addressing any of the above topics, or related issues, we particularly welcome work involving Semitic languages with scarce resources. WORKSHOP FORMAT =============== The workshop will last for one day, June 29th, and will consist of: - An invited talk (by Salim Roukos, IBM T. J. Watson Research Center) - Several sessions of regular paper presentations - A panel discussion drawing on aspects of the participating papers and their implications for future collaboration and coordination SUBMISSION INFORMATION ====================== Submissions will consist of regular full papers of max. 8 pages, formatted following the ACL 2005 guidelines (http://www.aclweb.org/acl2005/index.php?stylefiles). All submissions must be anonymous. Please send submissions in either .pdf or .ps form. Both submission and review processes will be handled electronically. In a separate email with subject SemCL05 please send names of Authors and name of contact person. We are pursuing the possibility of publishing a selection of accepted papers in a journal special issue on Semitic computational linguistics. IMPORTANT DATES =============== Regular paper submissions     April 10 Notification (short and regular papers)  May 4 Camera-ready papers       May 15 ORGANIZERS ========== Kareem Darwish (German University in Cairo, Egypt) kareem@darwish.org Mona Diab (Columbia University, USA) mdiab@cs.columbia.edu Nizar Habash (Columbia University, USA) habash@cs.columbia.edu CONTACT ======= For submissions, questions, comments, etc. please send email to semwksp-acl05@ccls.columbia.edu PROGRAM COMMITTEE ================= Ibrahim A. Alkharashi (King Abdulaziz City for Science and Technology, Saudi Arabia) Tim Buckwalter (Linguistic Data Consortium, USA) Violetta Cavalli-Sforza (Carnegie Mellon University, USA) Yaacov Choueka (Bar-Ilan University, Israel) Joseph Dichy (Lyon University, France) Martha Evens (Illinois Institute of Technology, USA) Ali Farghaly (SYSTRAN Software, Inc.) Alexander Fraser (USC/ISI) Andrew Freeman (Mitre) Alon Itai, (Technion, Israel) George Kiraz (Beth Mardutho: The Syriac Institute, USA) Katrin Kirchhoff (University of Washington, USA) Alon Lavie (Carnegie Mellon University, USA) Mohamed Maamouri (Linguistic Data Consortium, USA) Uzzi Ornan (Technion, Israel) Anne De Roeck (Open University, UK) Michael Rosner (University of Malta, Malta) Salim Roukos (IBM, USA) Khalil Sima'an (University of Amsterdam, Netherlands) Abdelhadi Soudi (ENIM, Rabat, Morocco) Shuly Wintner (University of Haifa, Israel) Remi Zajac (SYSTRAN Software, USA) ";
    find = Cli.performGrade(Cli.searchDate(chaine), Cli.searchKeyWords(chaine));
    System.out.println(find.toString());
    
    System.out.println("Test avec l'email 10 du corpus sample :");
    chaine = "APOLOGIES FOR MULTIPLE POSTINGS Merci de diffuser le plus largement possible cet appel à participation. Appel à participation TIA 2005 « Terminologie et Intelligence Artificielle» Rouen, 4 -5 avril 2005 http://www.univ-rouen.fr/dyalang/tia2005 Deux dates à ne pas oublier !! * 28 février : date limite pour une réservation d'hotel à tarif préférentiel * 15 mars : majoration du prix pour les inscriptions tardives Nous vous invitons à vous inscrire dès maintenant à la conférence Terminologie et Intelligence Artificielle (TIA 2005) qui aura lieu les 4 et 5 avril 2005 à Rouen. Des tarifs préférentiels ont été négociés avec des hôtels jusqu'au 28 février. De plus, les inscriptions tardives (après le 15 mars 2005) seront majorées. Contexte de la conférence : -------------------------------------------------- Du fait du développement de l'information en réseaux, qu'ils soient internes ou externes, institutions, entreprises et laboratoires se trouvent aujourd'hui confrontés à des problèmes considérables posés par la taille et la diversité des documents spécialisés. Depuis toujours le texte a été considéré comme un vecteur important d'informations et de connaissances, mais il est de plus en plus placé au centre de processus décisionnels, scientifiques ou techniques. Accéder à de vastes collections de documents, les archiver, identifier les documents pertinents, les diffuser de manière ciblée, identifier les connaissances utiles dans les textes, comparer ou confronter des contenus textuels, telles sont, entre autres, les tâches auxquelles la terminologie peut contribuer à apporter des réponses. Devant de tels enjeux, la terminologie, en tant que discipline, doit établir des relations coopératives avec un ensemble de disciplines allant de la linguistique aux sciences de l'information, en passant par le traitement automatique des langues, l'apprentissage artificiel et l'ingénierie des connaissances. Ces disciplines et la terminologie s'enrichissent mutuellement : la terminologie constitue des ressources exploitables par ces diverses disciplines qui, en retour, peuvent fournir des théories, méthodes et outils pour la construction des ressources terminologiques (index, terminologies, thésaurus, ontologies...). Les thèmes de la conférences ainsi qu'une version préliminaire du programme sont disponibles sur le site de la conférence : http://www.univ-rouen.fr/dyalang/tia2005 Comité de programme ------------------- Président : Yannick Toussaint (LORIA  INRIA, Nancy) Brigitte Biebow (LIPN,Villetaneuse) Myriam Bouveret ( DYALANG, Rouen) Maria-Teresa Cabré (Universitat Pompeu Fabra, Espagne) Farid Cerbah (Dassault Aviation, Paris) Jean Charlet (AP-HP, Paris) James Cussens (University of York, Grande-Bretagne) Valérie Delavigne (Dyalang, Rouen) Rose Dieng (INRIA, Sophia) Ulrich Heid (Universität Stuttgart , Allemagne) Fidelia Ibekwe-San Juan (Université Lyon 3) Sylvie Lainé (Université Lyon 3) Marie-Claude L'Homme (Université de Montréal, Canada) Pascale Sébillot (IRISA, Rennes) Monique Slodzian (CRIM-INALCO, Paris) Comité d'organisation --------------------- Présidente : Valérie Delavigne, DYALANG Kristina Alexandru-Nicolae, DYALANG Nathalie Avenel, IRED, Université de Rouen Nathalie Baudouin, DYALANG-PSI Myriam Bouveret, DYALANG Evelyne Delabarre, DYALANG Régine Delamotte, DYALANG François Gaudin, DYALANG Maryvonne Holzem, DYALANG-PSI Philippe Jeanne, CNRS-DYALANG Mito Katano, DYALANG Aurélie Neveol, PSI Contact :TIA2005@univ-rouen.fr ";
    find = Cli.performGrade(Cli.searchDate(chaine), Cli.searchKeyWords(chaine));
    System.out.println(find.toString());
    
    System.out.println("Test avec l'email 9 du corpus sample :");
    chaine = " CALL FOR PAPERS Cross-Language Knowledge Induction Workshop International Workshop held as part of the Eurolan 2005 Summer School 25 July - 6 August, 2005, Cluj-Napoca, Romania Website: http://www.site.uottawa.ca/~diana/Eurolan2005KnowledgeInductionWorkshop.htm Knowledge of words and text behavior in other languages has recently been used to help solving tasks in a first language. An example of such a task is word sense disambiguation by using translations in a second language. Another example is verb classification by studying properties of verbs in several languages. A second modality of knowledge transfer across languages is to take advantage of resources already built for English and for a few other resource-rich languages. These resources have been used to induce knowledge in languages for which few linguistic resources are available. This was made possible by the wider availability of parallel corpora (better alignment methods at paragraph, sentence, and word level). Examples of knowledge induction tasks are: learning morphology, part-of-speech tags, and grammatical gender. The development of wordnets for many languages used as a starting point knowledge transfer from the Princeton WordNet. This workshop will provide a forum for discussion between leading names in the field and researchers involved in cross-language applications. We would like to invite researchers, master and Ph.D. students, to submit their original and unpublished work to the workshop. Special consideration will be given to papers for which one of the languages involved in the knowledge transfer is an East-European language. Topics of interest include, but are not limited to: * applications that exploit parallel corpora (aligned at paragraph, sentence, or word level). * induction of knowledge from a language for which resources are abundant to another language for which fewer resources are available. * using other languages to solve a task in a first language: - word-sense disambiguation by using translations in other languages. - verb classification by studying verb properties in several languages. - other tasks of this kind * identifying and using cognate words between languages. * building wordnets by knowledge transfer. * exploiting multi-language wordnets for NLP applications. ============================================== Submission Requirements Authors are invited to submit a 4-6 pages extended abstract in electronic form (pdf only) by 5th of May 2005. Authors of accepted papers should submit the final version in electronic format not later than 20th of June. The final version must be in pdf format. If you have problems delivering your paper in pdf format, please contact the organizing committee. We can assist you with converting from Word format into pdf format. If your paper is a ps file, please convert into pdf and make sure all the fonts are included. The maximum length of the paper should be 8 pages. This workshop uses the same guidelines as ACL-2005. The instructions can be found at http://www.aclweb.org/acl2005/index.php?stylefiles. Please do not insert page numbers, headers or footers. If you have any problem following the style please contact the organizing committee as soon as possible. All the papers should be sent to diana@site.uottawa.ca ============================================== Important Dates Submission Deadline: 5th May 2005 Notification of Acceptance: 5th June 2005 Camera-ready Papers: 20th June 2005 Demos of working or under development systems are encouraged. ========================================== Registration People wanting to attend the workshop must be registered at the Eurolan 2005 School (http://www.cs.ubbcluj.ro/eurolan2005). Participation to the workshop is open to all Eurolan 2005 attendants. Copies of workshop proceedings will be made available. Authors of the papers accepted for presentation at the workshop will benefit of early registration fee no matter the date they register. ========================================== Programme Committee Eneko Agirre (University of the Basque Country, Donostia-San Sebastian, Spain) Paul Buitelaar (DFKI, Saarbrucken, Germany) Silviu Cucerzan (Microsoft Research, US) Mona Diab (Columbia University, US) Lluis Marquez (Universitat Politecnica de Catalunya, Barcelona, Spain) Joel Martin (National Research Council of Canada) Rada Mihalcea (University of North Texas, US) Viviana Nastase (University of Ottawa, Canada) Ted Pedersen (University of Minnesota, Duluth, US) Emanuele Pianta (ITC-IRST, Povo-Trento, Italy) Philip Resnik (University of Maryland, US) Laurent Romary (LORIA, Nancy, France) Michel Simard (Xerox Research Centre Europe, France) Suzanne Stevenson (University of Toronto, Canada) Amalia Todirascu (Technological University of Troyes, France) Dan Tufis (Romanian Academy, Bucharest, Romania) Nikolai Vazov (University of Sofia, Bulgaria) Organizing committee Diana Inkpen (University of Ottawa, Canada) Carlo Strapparava (ITC-IRST, Povo-Trento, Italy) ";
    find = Cli.performGrade(Cli.searchDate(chaine), Cli.searchKeyWords(chaine));
    System.out.println(find.toString());

  }

}
