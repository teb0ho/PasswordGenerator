import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * Created by Teb0ho on 2016-10-28.
 */
public class PasswordGen {

    public static void main(String[] args)  {

        String path = new File("").getAbsolutePath();

        System.out.println("Select the criteria you want for your password:\n" +
                "1. Randomly Mixed Characters\n" +
                "2. Name, Action, Object - Combination\n" +
                "3. Lower case alphabet, upper case alphabet & number combination");

        Scanner scan = new Scanner(System.in);
        int criteria;
        String passString = "";

        Random rand = new Random();

        try {
            criteria = scan.nextInt();

            if (criteria == 1) {

                int passLength;
                int[] length = new int[2];
                char[] chars = new char[94];
                int asciiChars = 33;

                length[0] = 8;
                length[1] = 10;


                passLength = length[rand.nextInt(2)];

                for (int i = 0; i < 94; i++ ) {

                    chars[i] = (char) asciiChars;
                    asciiChars++;
                }

                for (int i = 0; i < passLength; i++ ) {
                    passString += chars[rand.nextInt(94)];
                }

                System.out.println(passString);
            }

            else if (criteria == 2) {

                BufferedReader namestxt = new BufferedReader(new FileReader(path + "/res/names.txt"));
                ArrayList<String> fileNames = new ArrayList<String>();

                BufferedReader actionstxt = new BufferedReader(new FileReader(path + "/res/actions.txt"));
                ArrayList<String> fileActions = new ArrayList<String>();


                BufferedReader objectstxt = new BufferedReader(new FileReader(path + "/res/objects.txt"));
                ArrayList<String> fileObjects = new ArrayList<String>();


                String str;
                String names;
                String actions;
                String objects;

                while ((str = namestxt.readLine()) != null) {

                    fileNames.add(str);

                }

                while ((str = actionstxt.readLine()) != null) {

                    fileActions.add(str);

                }

                while ((str = objectstxt.readLine()) != null) {

                    fileObjects.add(str);

                }


                names = fileNames.get(rand.nextInt(345));
                actions = fileActions.get(rand.nextInt(1001));
                objects = fileObjects.get(rand.nextInt(100));

                passString = names + actions + objects;

                System.out.println(passString);

            }

            else if (criteria == 3) {

                char[] numbers = new char[10];
                char[] symbols = new char[15];
                char[] capAlph = new char[26];
                char[] smlAlph = new char[26];

                int nums = 48;
                int syms = 33;
                int caps = 65;
                int smls = 97;

                int opt;

                for (int i = 0; i < 10; i++) {
                    numbers[i] = (char) nums;
                    nums++;
                }

                for (int i = 0; i < 15; i++) {
                    symbols[i] = (char) syms;
                    syms++;
                }


                for (int i = 0; i < 26; i++) {
                    capAlph[i] = (char) caps;
                    caps++;
                }

                for (int i = 0; i < 26; i++) {
                    smlAlph[i] = (char) smls;
                    smls++;
                }


                for (int i = 0; i < 8; i++) {
                    opt = rand.nextInt(2);

                    if (opt == 1) {
                        passString += smlAlph[rand.nextInt(26)];
                    } else {
                        passString += capAlph[rand.nextInt(26)];
                    }
                }

                for (int i = 0; i < 4; i++) {
                    opt = rand.nextInt(2);

                    if (opt == 1) {
                        passString += numbers[rand.nextInt(10)];
                    }
                    else {
                        passString += symbols[rand.nextInt(15)];
                    }
                }


                System.out.println(passString);

            }

            File file = new File("res/output.txt").getAbsoluteFile();

            if(!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(passString + "\n");
            bufferedWriter.close();

        }

        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
