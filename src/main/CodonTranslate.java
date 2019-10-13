package main;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class to convert codons to amino acids. The class provides a static Map that
 * links each codon with its corresponding amino acid, and methods to manipulate this map and
 * provide a translation.
 *
 * This code is licensed under the Hippocratic License, for further details please consult the
 * LICENSE.txt file in the project folder.
 *
 * @author Michael Male
 * @version 1.0 13 October 2019
 */
public class CodonTranslate {

    private static final Map<String, Character> TRANSLATION_TABLE = initialiseMap();
    private String dna;
    private List<String> codonList;

    /**
     * A static method that inputs key-value pairs, where the key is a codon and the value is its
     * corresponding amino acid.
     * @return Map of key String and value Character containing codons with their amino acids.
     */
    private static Map<String, Character> initialiseMap() {
        Map<String, Character> codonTable = new HashMap<String, Character>();

        codonTable.put("UUU", 'F');
        codonTable.put("AUU", 'I');
        codonTable.put("GUU", 'V');
        codonTable.put("UUC", 'F');
        codonTable.put("CUC", 'L');
        codonTable.put("AUC", 'I');
        codonTable.put("GUC", 'V');
        codonTable.put("UUA", 'L');
        codonTable.put("CUA", 'L');
        codonTable.put("AUA", 'I');
        codonTable.put("GUA", 'V');
        codonTable.put("UUG", 'L');
        codonTable.put("CUG", 'L');
        codonTable.put("AUG", 'M');
        codonTable.put("GUG", 'V');
        codonTable.put("UCU", 'S');
        codonTable.put("CCU", 'P');
        codonTable.put("ACU", 'T');
        codonTable.put("GCU", 'A');
        codonTable.put("UCC", 'S');
        codonTable.put("CCC", 'P');
        codonTable.put("ACC", 'T');
        codonTable.put("GCC", 'A');
        codonTable.put("UCA", 'S');
        codonTable.put("CCA", 'P');
        codonTable.put("ACA", 'T');
        codonTable.put("GCA", 'A');
        codonTable.put("UCG", 'S');
        codonTable.put("CCG", 'P');
        codonTable.put("ACG", 'T');
        codonTable.put("GCG", 'A');
        codonTable.put("UAU", 'Y');
        codonTable.put("CAU", 'H');
        codonTable.put("AAU", 'N');
        codonTable.put("GAU", 'D');
        codonTable.put("UAC", 'Y');
        codonTable.put("CAC", 'H');
        codonTable.put("AAC", 'N');
        codonTable.put("GAC", 'D');
        codonTable.put("CAA", 'Q');
        codonTable.put("AAA", 'K');
        codonTable.put("GAA", 'E');
        codonTable.put("CAG", 'Q');
        codonTable.put("AAG", 'K');
        codonTable.put("GAG", 'E');
        codonTable.put("UGU", 'C');
        codonTable.put("CGU", 'R');
        codonTable.put("AGU", 'S');
        codonTable.put("GGU", 'G');
        codonTable.put("UGC", 'C');
        codonTable.put("CGC", 'R');
        codonTable.put("AGC", 'S');
        codonTable.put("GGC", 'G');
        codonTable.put("CGA", 'R');
        codonTable.put("AGA", 'R');
        codonTable.put("GGA", 'G');
        codonTable.put("UGG", 'W');
        codonTable.put("CGG", 'R');
        codonTable.put("AGG", 'R');
        codonTable.put("GGG", 'G');
        codonTable.put("CUU", 'L');

        return codonTable;
    }

    /**
     * A static method that initialises the codon list, splitting a given DNA string into
     * multiples of three, ready for translation.
     * @return LinkedList of type String containing one-to-many codons from a given DNA string.
     * @throws IllegalArgumentException if either the String given is less than three characters,
     * the String contains illegal characters (characters that aren't uppercase letters), or the
     * length of the String is not divisible by three, which implies it is an incomplete DNA string.
     */
    private List<String> initialiseCodonList() throws IllegalArgumentException {
        List<String> codons = new LinkedList<>();
        int dnaLength = this.dna.length();

        /* Error checking */

        if (!dna.matches("[A-Z]+")) {
            throw new IllegalArgumentException("Illegal formatting of DNA string. The string must" +
                    " only contain uppercase letters.");
        }

        if (dnaLength < 3) {
            throw new IllegalArgumentException("The DNA string that was provided is too small. It" +
                    " must be at least three letters, length is actually " + dnaLength);
        }

        if (dnaLength % 3 != 0) {
            throw new IllegalArgumentException("Illegal length for DNA string. Length should be a" +
                    " value divisible by 3, length is actually " + dnaLength);
        }


        /* Iterative loop to add codons into a linked list. */
        for (int i = 0; i < dnaLength; i += 3) {
            codons.add(dna.substring(i, Math.min(dnaLength, i + 3)));
        }

        return codons;
    }

    /**
     * Constructor for objects of class CodonTranslate
     * @param dnaString A full DNA string containing codons.
     */
    public CodonTranslate(String dnaString) {
        this.dna = dnaString;
        this.codonList = initialiseCodonList();
    }

    /**
     * A method to convert a list of codons into their respective amino acids. It does this by
     * checking the static HashMap for matching codons, adding them into a LinkedList, and then
     * returning the completed LinkedList.
     *
     * @param printToConsole    If this boolean is set to true, then the method will also print
     *                          the processed linked list to the console, before returning the list.
     * @return  List of type LinkedList containing a translated DNA string.
     * @throws IllegalArgumentException if the parsed list contains an invalid codon i.e. a codon
     * that does match with any key in the HashMap.
     */
    public List<Character> convertToAminoAcid(boolean printToConsole) throws IllegalArgumentException {
        List<Character> aminoAcids = new LinkedList<>();

        for (String codon : this.codonList) {

            /* Checks if the current codon is a stop codon, and if so breaks the loop so as not
            to process any more codons.
             */
            if (codon.equals("UAA") || codon.equals("UAG") || codon.equals("UGA")) {
                break;
            }

            // Initially checks if there is a codon by that name in the table. If there is not
            // then throws an exception to specify that the string is invalid.
            if (!TRANSLATION_TABLE.containsKey(codon)) {
                throw new IllegalArgumentException("One or more codons in the string are" +
                        " invalid. Please check the string and try again.");
            } else {
                char translated = TRANSLATION_TABLE.get(codon);
                aminoAcids.add(translated);
            }
        }

        // Will print the completed list to console should the boolean flag be set true.
        if (printToConsole) {
            System.out.println(aminoAcids.stream().map(String::valueOf)
                    .collect(Collectors.joining()));
        }

        return aminoAcids;
    }
    }

