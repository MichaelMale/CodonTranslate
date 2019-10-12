package main;

/**
 * A class to convert codons to amino acids and vice-versa. The class provides a static Map that
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

    /* String array to contain list of all codons */
    private static final String[] CODONS = {
            "UUU", "AUU", "GUU", "UUC", "CUC", "AUC", "GUC", "UUA", "CUA", "UUG", "CUG", "UCU",
            "CCU", "ACU", "GCU", "UCC", "CCC", "ACC", "GCC", "UCA", "CCA", "ACA", "GCA", "UCG",
            "CCG", "ACG", "GCG", "UAU", "CAU", "AAU", "GAU", "UAC", "CAC", "AAC", "GAC", "CAA",
            "AAA", "GAA", "CAG", "AAG", "GAG", "UGU", "CGU", "AGU", "GGU", "UGC", "CGC", "AGC",
            "GGC", "CGA", "AGA", "GGA", "UGG", "CGG", "AGG", "GGG", "CUU" };

    /* String array to contain list of all amino acids, at the same position as it's
    corresponding codon
     */
    private static final char[] AMINO_ACIDS = {
            'F', 'I', 'V', 'F', 'L', 'I', 'V', 'L', 'L', 'I', 'V', 'L', 'L', 'M', 'V', 'S', 'P',
            'T', 'A', 'S', 'P', 'T', 'A', 'S', 'P', 'T', 'A', 'S', 'P', 'T', 'A', 'Y', 'H', 'N',
            'D', 'Y', 'H', 'N', 'D', 'Q', 'K', 'E', 'Q', 'K', 'E', 'C', 'R', 'S', 'G', 'C', 'R',
            'S', 'G', 'R', 'R', 'G', 'W', 'R', 'R', 'G', 'L'};


    public CodonTranslate() {
        if (CODONS.length != AMINO_ACIDS.length) {
            System.err.println("Discrepancy in length of codon and amino acid arrays.");
        }
    }
    }

