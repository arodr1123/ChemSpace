
import chemaxon.formats.MolImporter;
import chemaxon.sss.SearchConstants;
import chemaxon.sss.search.*;
import chemaxon.struc.Molecule;

import java.util.ArrayList;
import java.util.Arrays;


public class SubSearch {

    //Currently updating this module to store the hits instead of just printing them
    ArrayList<String> storedHits = new ArrayList<String>();

    /*
     *@param t target of the Subtructure Search
     *@param q query of the Subtructure Search
     */
    public void run(String t, String q) throws SearchException, Exception {
        //read query and target molecules
        Molecule query = MolImporter.importMol(q);
        Molecule target = MolImporter.importMol(t);

        //create searcher
        MolSearch ms = new StandardizedMolSearch();
        ms.setTarget(target);
        ms.setQuery(query);

        MolSearchOptions searchOptions = new MolSearchOptions(SearchConstants.SUBSTRUCTURE);

        ms.setSearchOptions(searchOptions);

        //Finding Substructure
        System.out.println("Is query matching target?");
        System.out.println(ms.isMatching() ? "yes" : "No");

        //Hits count
        System.out.println("Number of hits");
        System.out.println(ms.getMatchCount());
        printHits(ms);

        //Sensitive Hits count
        System.out.println("Number of order sensitive hits: ");
        searchOptions.setOrderSensitiveSearch(true);
        ms.setSearchOptions(searchOptions);
        System.out.println(ms.getMatchCount());
        printHits(ms);

    }

    /*
     * @param molsearch
     * @throws Exception
     * printHits() prints the Substructure Search hits
     */
    private void printHits(MolSearch molSearch) throws Exception {

        int[] hit = molSearch.findFirst();
        while (hit != null) {

            System.out.println(Arrays.toString(hit));
            hit = molSearch.findNext();
        }
        System.out.println();
    }
}