
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileRead {

    ArrayList<String> smirkList = new ArrayList<String>();
    ArrayList<String> educt1 = new ArrayList<String>();
    ArrayList<String> educt2 = new ArrayList<String>();
    ArrayList<String> canonSmiles = new ArrayList<String>();
    SubSearch ss = new SubSearch();

    /*
     * fileRead1 is what being currently used to select SMIRKS file(s) via JFileChooser since it was able to select
     * multiple files at a time. After compiling the program in the terminal this method will be phased out.
     *
     */
    public void fileRead1() {

        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setDialogTitle("Select Directory:");
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            System.out.println("Directories found\n");
            Arrays.asList(files).forEach(x -> {
                if (x.isDirectory()) {
                    System.out.println(x.getName());
                }
            });
            System.out.println("\n- - - - - - - - - - -\n");
            System.out.println("Files Found\n");
            Arrays.asList(files).forEach(x -> {
                if (x.isFile()) {
                    //System.out.println(x.getAbsolutePath());
                    readSmirks(x.getAbsolutePath());

                }
            });
        }
    }

    /*
     * fileRead1 is what being currently used to select SMILES file(s) via JFileChooser since it was able to select
     * multiple files at a time. After compiling the program in the terminal this method will be phased out.
     */
    public void fileRead2() {

        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setDialogTitle("Select Directory:");
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            System.out.println("Directories found\n");
            Arrays.asList(files).forEach(x -> {
                if (x.isDirectory()) {
                    System.out.println(x.getName());
                }
            });
            System.out.println("\n- - - - - - - - - - -\n");
            System.out.println("Files Found\n");
            Arrays.asList(files).forEach(x -> {
                if (x.isFile()) {
                    //System.out.println(x.getAbsolutePath());
                    readSmiles(x.getAbsolutePath());

                }
            });
        }
    }

    /*
     * readSmirks reads through the csv files and assigns the columns to the appropriate variables.
     * @param csvFile SMIRKS csv file
     */
    public void readSmirks(String csvFile) {

        BufferedReader br = null;
        String line = "";
        //deliminiter
        String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        try {

            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] smirk = line.split(cvsSplitBy, -1);

                //testing of String annotation
//                System.out.println("[" + smirk[0].replace("\"", "") + " , "
//                        + smirk[1].replace("\"", "") + " , "
//                        + smirk[2].replace("\"", "") + " , "
//                        + smirk[3].replace("\"", "") + "]");

                //aggregation of the values (SMIRKS, EDUCT_1 smiles, EDUCT_2 smiles)of the specified columns
                smirkList.add(smirk[1].replace("\"", ""));
                educt1.add(smirk[2]);
                educt2.add(smirk[3]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*
     * readSmirks reads through the csv files and assigns the columns to the appropriate variables.
     * @param csvFile SMILES csv file
     */
    public void readSmiles(String csvFile) {

        BufferedReader br = null;
        String line = "";
        //deliminiter
        String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        try {

            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] smile = line.split(cvsSplitBy, -1);

                //testing of String annotation
//                System.out.println("[" + smile[0].replace("\"", "") + " , "
//                        + smile[1].replace("\"", "") + " , "
//                        + smile[2].replace("\"", "") +  "]");

                //aggregation of the values (cannonical smiles) of the specified columns
                canonSmiles.add(smile[1]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*
     * startSubSearch() executes the Substructure search after it was given the lists of SMILES and SMIRKS.
     * @throws Exception
     */
    public void startSubSearch() throws Exception {


        //The canonical smiles is being matched with every possible pair of EDUCT_1 & EDUCT2 smiles
        for (int i = 1; i < canonSmiles.size(); i++) {

            for (int j = 1; j < educt1.size(); j++) {

                try {

                    //Checking if a hit was discovered with EDUCT_1
                    System.out.println("\n" + "Target: " + canonSmiles.get(i) + "\n"
                            + "educt_1: " + educt1.get(j));
                    new SubSearch().run(canonSmiles.get(i), educt1.get(j));

                    //Checking if a hit was discovered with EDUCT_2
                    System.out.println("educt_2: " + educt2.get(j));
                    ss.run(canonSmiles.get(i), educt2.get(j));

                } catch (Exception e) {

                    //System.err.println("Unexpected error during search");
//                    e.printStackTrace();

                }
            }
        }

        //TODO: save hits
    }
}