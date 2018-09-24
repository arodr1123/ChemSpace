import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Reactor {

    /*
     * Reactor() is where all the hits from the Substructure Search will go to execute a reaction. Im currently
     * configuring this module to process the call to Reactor with less overhead and faster results, it is currently too
     * slow to finalize.
     */

//    void Reactor() {
//        Process p = Runtime.getRuntime().exec("cmd.exe /c start cd C:\\Program Files\\ChemAxon\\JChemSuite\\bin\\&&react");
//
//        ProcessBuilder builder = new ProcessBuilder(
//                "cmd.exe", "/c", "cd \"C:\\\\Program Files\\\\ChemAxon\\\\JChemSuite\\\\bin\\\\\" && react");
//        builder.redirectErrorStream(true);
//        Process p = builder.start();
//        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String line;
//
//        while (true) {
//            line = r.readLine();
//            if (line == null) {
//                break;
//            }
//            System.out.println(line);
//        }
//    }
}