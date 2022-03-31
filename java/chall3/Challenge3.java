import java.io.*;
import java.util.*;

class Challenge3 {
    public static void main(String args[]) {
        // First part stuff
        // we build our result based on most common bits to get our gamma rate value
        // char[] res = new char[12];
        int[] res = new int[12];
        // we want to iterate through each bit one at a time
        // calculate the most common value from each bit of each position
        File file = new File("input.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                // grab current element from current line
                String currLine = sc.nextLine();
                for (int i = 0; i < currLine.length(); i++) {
                    if (currLine.charAt(i) == '1') {
                        res[i]++;
                    }
                    else {
                        res[i]--;
                    }
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Crash: " + e);
        }

        // iterate through our res: if negative then it is a 0, if positive then it is a 1
        String gamma = new String();
        String epsilon = new String();
        for (int i = 0; i < res.length; i++) {
            if (res[i] >= 1) {
                gamma += "1";
                epsilon += "0";
            }
            else {
                gamma += "0";
                epsilon += "1";
            }
        }
        // now we can convert it to the int value from the binary we had
        int gammaDecimal = Integer.parseInt(gamma, 2);
        // we should also get our epsilon and as the inverse and convert it too
        int epsilonDecimal = Integer.parseInt(epsilon, 2);
        int powerConsumption = gammaDecimal * epsilonDecimal;
        System.out.println("Power Consumption Value: " + powerConsumption);

        // Part 2:
        // We need to multiply oxygen generator rating * CO2 scrubber rating
        ArrayList<String> diagnosticList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                diagnosticList.add(sc.nextLine());
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Crash: " + e);
        }

        // Fix Later: Making a copy of diagnosticList to use for the c02 scrubber
        ArrayList<String> scrubberList = (ArrayList<String>)diagnosticList.clone();

        // Find oxygen generator rating
        while (diagnosticList.size() != 1) {
            // we iterate through the list until there is just the 1 number left
            // String diagLen = diagnosticList.get(0);
            int count[] = new int[12];

            // Note: This is just here for debugging purposes
            if (diagnosticList.size() < 1) {
                System.out.println("Something went wrong");
                break;
            }

            // Iterate through our current list
            // for (int i = 0; i < diagLen.length(); i++) {
            // get the current element and iterate through that
            for (int i = 0; i < diagnosticList.size(); i++) {
                String currLine = diagnosticList.get(i); // grab current string to see what bit it is
                // System.out.println(currLine);

                // so basically it is trying to get it removing anything that does not have a 1 as its major thing
                for (int j = 0; j < currLine.length(); j++) {
                    if (currLine.charAt(j) == '1') {
                        count[j]++;
                    }
                    else {
                        count[j]--;
                    }
                }
            }
            // }
            for (int i = 0; i < count.length; i++) {
                // get the current element and iterate through that
                for (int j = 0; j < diagnosticList.size(); j++) {
                    String currElem = diagnosticList.get(j);
                    if (count[i] >= 0 && currElem.charAt(i) != '1') {
                        // we know our majority at this current elem is 1 or equal
                        diagnosticList.remove(j);
                    }
                    else if (count[i] < 0 && currElem.charAt(i) != '0') {
                        diagnosticList.remove(j);
                    }
                }
            }
        }

        System.out.println("We got out!");
        for (int i = 0; i  < diagnosticList.size(); i++) {
            System.out.println("Size: " + diagnosticList.size());
            System.out.println("We got: " + diagnosticList.get(i));
        }

        // Find CO2 scrubber rating
        while (scrubberList.size() != 1) {
            // we iterate through the list until there is just the 1 number left
            // String diagLen = diagnosticList.get(0);
            int count[] = new int[12];

            // Note: This is just here for debugging purposes
            // System.out.println("Curr Size: " + scrubberList.size());
            if (scrubberList.size() < 1) {
                System.out.println("Something went wrong");
                break;
            }

            // Iterate through our current list
            // for (int i = 0; i < diagLen.length(); i++) {
            // get the current element and iterate through that
            for (int i = 0; i < scrubberList.size(); i++) {
                String currLine = scrubberList.get(i); // grab current string to see what bit it is
                // System.out.println(currLine);

                // so basically it is trying to get it removing anything that does not have a 1 as its major thing
                for (int j = 0; j < currLine.length(); j++) {
                    if (currLine.charAt(j) == '1') {
                        count[j]++;
                    }
                    else {
                        count[j]--;
                    }
                }
            }
            // }
            for (int i = 0; i < count.length; i++) {
                // get the current element and iterate through that
                if (scrubberList.size() == 1) {
                    break;
                }
                for (int j = 0; j < scrubberList.size(); j++) {
                    String currElem = scrubberList.get(j);
                    // System.out.println("CurrCount: " + count[i] + " CurrElem: " + currElem.charAt(i));
                    if (count[i] >= 0 && currElem.charAt(i) == '1') {
                        // we know our majority at this current elem is 1 or equal\
                        // System.out.println("Removing the 1 majority from: " + count[i] + " " + currElem.charAt(i));
                        scrubberList.remove(j);
                    }
                    else if (count[i] < 0 && currElem.charAt(i) == '0') {
                        // System.out.println("Removing the 0 majority from: " + count[i] + " " + currElem.charAt(i));
                        scrubberList.remove(j);
                    }
                }
            }
        }

        System.out.println("We got out from scrubberList!");
        for (int i = 0; i  < scrubberList.size(); i++) {
            System.out.println("Size: " + scrubberList.size());
            System.out.println("We got: " + scrubberList.get(i));
        }

        // Now we want to convert the binary numbers to their decimal forms
        int oxygenGeneratorRating = Integer.parseInt(diagnosticList.get(0), 2);
        // we should also get our epsilon and as the inverse and convert it too
        int c02ScrubberRating = Integer.parseInt(scrubberList.get(0), 2);
        int lifeSupportRating = oxygenGeneratorRating * c02ScrubberRating;
        System.out.println("Life Support Rating Number: " + lifeSupportRating);
    }
}