package tests;

import main.CodonTranslate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
class CodonTranslateTest {
    private final String rna =
            "AUGCCCAUGGGAUUAGUGUGGCACAAACAAGGACCACUAGAAAGGAUAUCUAUAAGAGGAGUAAUAGGAGUUAGGAGCGGGUAUAAC" +
                    "GAAACCAUUCGAAGGAAUUGGGUCAUGUUAGUAAGUAAAAGCGCCUUAUUCGUAUCCACAUGCUGCCAAUGUAACCCCC" +
                    "CUUACCUGACUUGUUAUAAGCAGUUGAAGAGUCCAGACGUGACACGUUUUGCGCGCGCUCAUGACAUGGAUCAUUUUAG" +
                    "AGACCACACUCAUAUGGCUGGGAGGACCAACUUGGAACAAACGUUUUGUGCUCAA";
    private final String correctAminoAcid =
            "MPMGLVWHKQGPLERISIRGVIGVRSGYNETIRRNWVMLVSKSALFVSTCCQCNPPYLTCYKQLKSPDVTRFARAHDMDHFRDHTH" +
                    "MAGRTNLEQTFCAQ";
    private final String dna = rna.replace("U", "T");

    @Test
    @DisplayName("Return a String of amino acids")
    void testRNATranslation() {
        CodonTranslate codonTranslate = new CodonTranslate(rna, true);
        List<Character> aminoAcid = codonTranslate.convertToAminoAcid(false);
        String aminoString = aminoAcid.stream().map(String::valueOf)
                .collect(Collectors.joining());

        assertEquals(correctAminoAcid, aminoString);
    }

    @Test
    @DisplayName("Convert DNA into RNA then return a String of amino acids")
    void testDNATranslation() {
        CodonTranslate codonTranslate = new CodonTranslate(dna, false);
        List<Character> aminoAcid = codonTranslate.convertToAminoAcid(false);
        String aminoString = aminoAcid.stream().map(String::valueOf)
                .collect(Collectors.joining());

        assertEquals(correctAminoAcid, aminoString);
    }

    @Test
    @DisplayName("Throw exception if RNA string is too small")
    void rnaStringTooSmall() {
        CodonTranslate codonTranslate = new CodonTranslate();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                codonTranslate.createTranslator("A", true));
        assertEquals("The DNA string that was provided is too small. It must be at " +
                "least three " +
                "letters, length is actually 1", exception.getMessage());
    }

    @Test
    @DisplayName("Throw exception if RNA string is lowercase")
    void rnaStringIsLowercase() {
        CodonTranslate codonTranslate = new CodonTranslate();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                codonTranslate.createTranslator(rna.toLowerCase(), true));
        assertEquals("Illegal formatting of DNA string. The string must only contain uppercase " +
                "letters.", exception.getMessage());
    }

    @Test
    @DisplayName("Throw exception if RNA string not divisible by three")
    void rnaStringModNotZero() {
        CodonTranslate codonTranslate = new CodonTranslate();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> codonTranslate.createTranslator(rna.substring(0, 299), true));
        assertEquals("Illegal length for DNA string. Length should be a value divisible by 3, " +
                "length is actually 299", exception.getMessage());
    }

    @Test
    @DisplayName("Throw exception if RNA string has numbers and symbols in it")
    void rnaStringNonAlpha() {
        CodonTranslate codonTranslate = new CodonTranslate();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> codonTranslate.createTranslator(rna + "0123456!!223", true));
        assertEquals("Illegal formatting of DNA string. The string must only contain uppercase " +
                "letters.", exception.getMessage());
    }
}
