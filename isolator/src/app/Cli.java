package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Command Line Interface class, this class contains the main function to
 * compile the command line program.
 * 
 * @author Clément Sipieter <csipieter@gmail.com>
 * @version 0.1
 */
public class Cli {

	private static final String TITLE = "isolator";

	private static String input = "";
	private static String path_output = "";

	/**
	 * Main
	 * 
	 * @param args
	 *            see --help.
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		try {
			input = args[0];
			path_output = args[1];

			System.out.println("input : " + input);
			System.out.println("output : " + path_output);

			InputStream ips = new FileInputStream(input);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);

			String line;

			PrintWriter file_out = null;
			int nb = 0;
			while ((line = br.readLine()) != null) {
				if (nb == 0 || beginEmail(line)) {
					System.out.println("New email detected");
					if (nb > 0) {
						file_out.close();
					}

					file_out = new PrintWriter(new BufferedWriter(
							new FileWriter(path_output + "/corpus_" + nb)));
					++nb;
				}

				file_out.println(line);
			}
			br.close();

		} catch (FileNotFoundException e) {
			System.err
					.println("An error has occured while opening or creating file.");
			printHelp();
			throw e;
		} catch (Exception e) {
			printHelp();
			throw e;
		}
	}

	private static boolean beginEmail(String line) {
		return line.startsWith("From - ", 0);
	}

	private static void printHelp() {
		System.out.println("Usage:\n" + "isolator INPUT_FILE OUTPUT_DIRECTORY");
	}

}
