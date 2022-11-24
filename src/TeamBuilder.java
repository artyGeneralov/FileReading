import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/*
 * pumpkin competition
 * Authors:
 * Artyom Generalov 319525671
 * Itamar Abir 208273169
 * 
 * */
public class TeamBuilder {
	public static void main(String[] args) {

		final int MAX_TEAMS = 4;
		ArrayList[] ar = new ArrayList[MAX_TEAMS];
		ArrayList rank_1 = (ar[0] = new ArrayList<String>());
		ArrayList rank_2 = (ar[1] = new ArrayList<String>());
		ArrayList rank_3 = (ar[2] = new ArrayList<String>());
		ArrayList rank_4 = (ar[3] = new ArrayList<String>());

		File src = new File("teams.txt");
		File dest = new File("rank.txt");

		// Read from file teams.txt
		try {
			FileReader in = new FileReader(src);
			int c;
			while ((c = in.read()) != -1) {
				// read the whole line (for simplicities sake)
				String wholeLine = "";
				do {
					wholeLine += (char) c;
					c = in.read();
				} while (c != '\n' && c != -1);

				// parse number and name from the string
				int teamPosition = Integer
						.parseInt(wholeLine.substring(wholeLine.indexOf(' ') + 1, wholeLine.indexOf(' ') + 2));
				String name = wholeLine.substring(0, wholeLine.indexOf(' '));
				ar[teamPosition - 1].add(name);
			}
			in.close();
		} catch (IOException e) {
			System.err.println("Could not open file");
			e.printStackTrace();
		}

		// Write to file rank.txt
		try {
			FileWriter out = new FileWriter(dest);
			int count = 1;

			for (ArrayList<String> elem : ar) {

				out.write(Character.forDigit(count, 10));
				while (!elem.isEmpty()) {
					String s = elem.get(0);
					out.write(' ');
					for (char c : s.toCharArray())
						out.write(c);
					elem.remove(0);
				}
				out.write('\n');
				count++;
			}
			out.close();
		} catch (IOException e) {
			System.err.println("Could not write to file");
		}

	}

}
