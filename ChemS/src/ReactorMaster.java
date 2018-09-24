
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReactorMaster {

    /*
    *@param args
    *Arguments for this project will be of the SMILES and SMIRK files
    */
    public static void main(String[] args) throws Exception {

       // System.out.println("Enter ");
        FileRead fr = new FileRead();
        fr.fileRead1();
        fr.fileRead2();

        /*
        * Currently compiling the program in the terminal, integration of the arguments will be finalized upon
        * completion
        *
        * java ReactorMaster smirkfile.csv smilesfile.csv
        */

        //fr.readSmirks(arg[1]);
        //fr.readSmiles(arg[2]);

        fr.startSubSearch();
    }
}