import java.util.ListIterator;
import java.util.Scanner;
import java.util.Vector;

public class StudentsScoreProcessor {

	/**
	 * TASK2 This program accepts student records (roll number and score) and
	 * prints them in decreasing order of scores.
	 * 
	 * @author Sergey Lapenkov
	 * @param args
	 */
	public static void main(String[] args) {
		// initialize
		StudentsScoreProcessor proc = new StudentsScoreProcessor();
		// input data
		Vector<String> db = proc.inputData(args);
		proc.print(db);
		// process data
		Vector<String> result = proc.sortAndTrim(db);
		// output data
		proc.print(result);
	}

	/**
	 * Input student data by two ways - from program arguments, of if program
	 * has no arguments - from console input
	 * 
	 * @param args
	 * @return
	 */
	private Vector<String> inputData(String[] args) {
		Vector<String> list = new Vector<String>();
		String accept = "";
		// input selection
		if (args.length > 0) { // if arguments no empty
			System.out.println("Processing program arguments by command line.");
			for (int i = 0; i < args.length; i++) {
				list.add(args[i]);
			}
		} else { // else console input
			Scanner input = new Scanner(System.in);
			do {
				System.out
						.println("Enter student data RRRR-SS, leave blank to exit");
				accept = input.nextLine();
				if (accept.length() > 0) { // if not empty
					if (accept.matches("\\d\\d\\d\\d-\\d\\d")) {
						// if format is correct - add to vector
						list.add(accept);
					} else {
						System.out.print("Incorrect format! ");
					}
				} else { // if empty - exit do-while
					break;
				}
			} while (true); // run forever
			input.close();
		}
		return list;
	}

	/**
	 * Sorting students data in decreasing order of score and remove duplicated
	 * elements
	 * 
	 * @param data
	 * @return
	 */
	private Vector<String> sortAndTrim(Vector<String> data) {

		Vector<String> output = new Vector<String>();

		do {
			int tmpScore = 0;
			int curScore = 0;
			String tmpRecord = "";

			// finding record with greatest score
			for (int index = 0; index < data.size(); index++) {
				curScore = Integer.parseInt(data.get(index).substring(5, 7));
				if (curScore > tmpScore) {
					tmpScore = curScore;
					tmpRecord = data.get(index).substring(0, 4);
				}
			}

			// add it to new vector
			output.add(tmpRecord + "-" + tmpScore);

			// remove duplicate records
			ListIterator<String> iter = data.listIterator();
			while (iter.hasNext()) {
				String tmp = iter.next();
				if (tmp.substring(0, 4).equals(tmpRecord)) {
					iter.remove();
				}
			}
		} while (data.size() > 0); // repeat until data is not empty

		return output;
	}

	/**
	 * Print list of students data
	 * 
	 * @param data
	 */
	private void print(Vector<String> data) {
		String[] out = { "" };
		out = data.toArray(out);
		System.out.println("Students list:");
		for (String s : out) {
			System.out.println(s);
		}
	}
}
