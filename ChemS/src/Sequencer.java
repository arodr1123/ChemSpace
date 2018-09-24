
public class Sequencer {

    /**
     * Producing all possible products by running all possible
     * sequences. (Algorithm in process)
     */
    void sequence(){

        String[] testList = {"A","B","C","D"};

        for (int i = 0; i < testList.length; i++){

            for(int j = i + 1; j < testList.length; j++){

                System.out.println(testList[i] +  "+" + testList[j]);

                boolean foundExtra = false;

                for(int k =0; k < testList.length && !foundExtra; k++){


                }
            }
        }
    }
}

