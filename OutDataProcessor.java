/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aditya
 */
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class OutDataProcessor {
    String inName;
    String outName;
    Pattern linePattern;
    Matcher lineMatcher;
    String[] lastValues;
    String[] currValues;
    BufferedWriter out1;

    public OutDataProcessor(String in, String out) throws IOException {
        linePattern = Pattern.compile("([\t ]*)(([\"])|([0-3]{0,1}[0-9][/][0-9]{2}[/][0-9]{4}))(.*)");
        lastValues = new String[8];
        currValues = new String[8];
        outName=out;
        inName=in;
        out1 = new BufferedWriter(new FileWriter(new File(outName)));
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in=new Scanner(System.in);
        System.out.print("Enter the name of the transaction report generated by LIBSYS: ");
        String inName=in.next();
        System.out.print("Enter the name of the file you wish to store the processed data: ");
        String outName=in.next();
        in.close();
        OutDataProcessor obj = new OutDataProcessor(inName,outName);
        obj.extractLine();
        System.out.println("Processing Complete!!");
    }

    public void extractLine() throws FileNotFoundException, IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(new File(inName)))).useDelimiter("\n");
        int count = 0;
        while (in.hasNext()) {
            String nextLine = in.next();
            lineMatcher = linePattern.matcher(nextLine);
            if (lineMatcher.matches()) {
                extractFields(nextLine);

            }
        }
        out1.close();
        in.close();
    }

    public void extractFields(String nextLine) throws IOException {

        StringTokenizer token1 = new StringTokenizer(nextLine, " ");
        //int count = token1.countTokens();
        int currToken = 0;
        for (currToken = 0; currToken < 3; currToken++) {
            currValues[currToken] = token1.nextToken();
            changeField(currToken);
        }
        switch (currValues[2]) {
            case "Rgstrn": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = "";
                currValues[5] = "";
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Mod": {
                currValues[2] += " " + token1.nextToken();
            }
            case "Mod Bwr": {
            }
            case "Mod RV": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = "";
                currValues[5] = "";
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Cncl": {
                currValues[2] += " " + token1.nextToken();
            }
            case "Cncl RV": {
            }
            case "Cncl Rg": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = "";
                currValues[5] = "";
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Rnew": {
                currValues[2] += " " + token1.nextToken();
            }
            case "Rnew Rg": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = token1.nextToken();
                changeField(4);
                currValues[5] = "";
                token1.nextToken();
                currValues[6] = token1.nextToken();
                changeField(6);
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Delnqnt": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = "";
                currValues[5] = "";
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "CD": {
                currValues[2] += " " + token1.nextToken();
            }
            case "CD Fnd": {
            }
            case "CD Lost": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = "";
                currValues[5] = "";
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Issu": {
                currValues[2] += " " + token1.nextToken();
            }
            case "Issu CD": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = "";
                currValues[5] = "";
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Rmv": {
                currValues[2] += " " + token1.nextToken();
            }
            case "Rmv Bwr": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = "";
                currValues[5] = "";
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "ID": {
                currValues[2] += " " + token1.nextToken();
            }
            case "ID Chng": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = "";
                currValues[5] = "";
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Missng": {
                currValues[3] = "";
                currValues[4] = token1.nextToken();
                changeField(4);
                currValues[5] = token1.nextToken();
                changeField(5);
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Dmgd": {
                currValues[3] = "";
                currValues[4] = token1.nextToken();
                changeField(4);
                currValues[5] = token1.nextToken();
                changeField(5);
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Bindry": {
                currValues[3] = "";
                currValues[4] = token1.nextToken();
                changeField(4);
                currValues[5] = token1.nextToken();
                changeField(5);
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Deacsn": {
                currValues[3] = "";
                currValues[4] = token1.nextToken();
                changeField(4);
                currValues[6] = "";
                String s1 = token1.nextToken();
                if (token1.hasMoreTokens()) {
                    String s2 = token1.nextToken();
                    currValues[5] = s1;
                    changeField(5);
                    currValues[7] = s2;
                    changeField(7);
                } else {
                    currValues[5] = "";
                    currValues[7] = s1;
                    changeField(7);
                }
                break;
            }
            case "On": {
                currValues[2] += " " + token1.nextToken();
            }
            case "On Shlf": {
                currValues[3] = "";
                currValues[4] = token1.nextToken();
                changeField(4);
                currValues[5] = "";
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "CP": {
                currValues[2] += " " + token1.nextToken();
            }
            case "CP Stat": {
                currValues[3] = "";
                currValues[4] = token1.nextToken();
                changeField(4);
                currValues[5] = token1.nextToken();
                changeField(5);
                String s1 = token1.nextToken();
                if (token1.hasMoreTokens()) {
                    String s2 = token1.nextToken();
                    currValues[6] = s1;
                    changeField(6);
                    currValues[7] = s2;
                    changeField(7);
                } else {
                    currValues[6] = "";
                    currValues[7] = s1;
                    changeField(7);
                }
                break;
            }
            case "Wrt/Off": {
                currValues[3] = "";
                currValues[4] = token1.nextToken();
                changeField(4);
                currValues[6] = "";
                String s1 = token1.nextToken();
                if (token1.hasMoreTokens()) {
                    String s2 = token1.nextToken();
                    currValues[5] = s1;
                    changeField(5);
                    currValues[7] = s2;
                    changeField(7);
                } else {
                    currValues[5] = "";
                    currValues[7] = s1;
                    changeField(7);
                }
                break;
            }
            case "Chrging": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = token1.nextToken();
                changeField(4);
                currValues[5] = "";
                currValues[6] = token1.nextToken();
                changeField(6);
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Rchrgng": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = token1.nextToken();
                changeField(4);
                currValues[5] = "";
                currValues[6] = token1.nextToken();
                changeField(6);
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Dchrgng": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = token1.nextToken();
                changeField(4);
                currValues[5] = token1.nextToken();
                changeField(5);
                currValues[6] = token1.nextToken();
                changeField(6);
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
            case "Put": {
                currValues[2] += " " + token1.nextToken();
            }
            case "Put RV": {
                currValues[3] = token1.nextToken();
                changeField(3);
                currValues[4] = "";
                currValues[5] = "";
                currValues[6] = "";
                currValues[7] = token1.nextToken();
                changeField(7);
                break;
            }
        }
        for (currToken = 0; currToken < currValues.length; currToken++) {
            if (currToken < currValues.length - 1) {
                out1.write(currValues[currToken] + ",");
            } else if (currToken == currValues.length - 1) {
                out1.write(currValues[currToken] + "\n");
            }

            lastValues[currToken] = currValues[currToken];
        }

    }

    public void changeField(int i) {
        if (currValues[i].equals("\"")) {
            currValues[i] = lastValues[i];
        }
    }
}
