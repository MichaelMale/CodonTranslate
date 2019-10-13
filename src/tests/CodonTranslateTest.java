package tests;

import main.CodonTranslate;

import java.util.List;

/**
 * Class used to test the CodonTranslate class.
 *
 * @todo Change to JUnit 5
 *
 * @see CodonTranslate
 *
 * @author Michael Male
 * @version 1.0 13 October 2019
 */
public class CodonTranslateTest {



    public static void main(String[] args) {
        CodonTranslate codonTranslate = new CodonTranslate(
                "GACCCAGCAATGACGTATACATGGCTTAAT" +
                        "GAA", false);

        List<Character> bandicootProject = codonTranslate.convertToAminoAcid(true);
    }
}
