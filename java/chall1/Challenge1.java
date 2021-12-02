import java.util.*;
import java.io.*;


class Challenge1 {

    public static void main (String args[]) {
        
        File file = new File("input.txt");
        System.out.println(part1(file));
        System.out.println(part2(file));
    }


    public static int part1(File file) {

        // First we want to read from the file
        int count = 0;
        try {
            Scanner sc = new Scanner(file);
            int prev = -1;
            
            while (sc.hasNextLine()) {
                // here we iterate through the loop and if we find something then we are good
                int curr = Integer.parseInt(sc.nextLine()); 
                if (prev == -1) {}
                else if (prev < curr){
                    count++;
                }
                prev = curr;
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return count;
    }

    public static int part2(File file) {

        List<Integer> inputList = new ArrayList<>();
        List<Integer> resList = new ArrayList<>();
        int count = 0;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                inputList.add(Integer.parseInt(sc.nextLine()));
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }

        // Now we read through our inputs and do sliding door technique
        for (int i = 0; i < inputList.size() - 2; i++) {
            resList.add(inputList.get(i) + inputList.get(i + 1) + inputList.get(i + 2));
        }

        // Next we loop through this window list and do the same as what we did in part 1
        int prev = Integer.MIN_VALUE;
        for (int i = 1; i < resList.size(); i++) {
            if (resList.get(i) > prev) {
                count++;
            }
            prev = resList.get(i);
        }
        return count;
    }
}