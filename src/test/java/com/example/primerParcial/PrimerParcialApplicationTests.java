package com.example.primerParcial;

import com.example.primerParcial.services.AdnService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = PrimerParcialApplication.class)
class PrimerParcialApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	AdnService adnService;

	@Test
	public void testHorizontal() {
		String[] dna = { "AAAAAT", "TGCAGT", "GCTTCC", "CCCCTG", "GTAGTC", "AGTCAC" };
		assertTrue(adnService.isMutant(dna));
	}
	@Test
	public void testVertical() {
		String[] dna = { "ATGCAA", "ATGCAA", "ATGCAA", "GTACAG", "CTCGCT", "TGTGAT" };
		assertTrue(adnService.isMutant(dna));
	}

	@Test
	public void testMutantDiagonalIzquierda() {
		String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
		assertTrue(adnService.isMutant(dna));
	}

	@Test
	public void testMutantDiagonalDerecha() {
		String[] dna = {"TGACTA", "GTGAGA", "AGTCTT", "GATCGG", "CTGCTA", "TCACTG"};
		assertTrue(adnService.isMutant(dna));
	}


	@Test
	public void testInvalidDNA() {
		String[] dnaInvalidChar = {"AXGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
		assertFalse(adnService.isMutant(dnaInvalidChar));

		String[] dnaNotSquare = {"ATGC", "CAGTGC", "TTATGT"};
		assertFalse(adnService.isMutant(dnaNotSquare));
	}


	@Test
	public void testNonMutant() {
		String[] dna = { "TCGTTA","ATGCAT","TCCTGT","AGAAGG","AGACGT","ACACTG" };
		assertFalse(adnService.isMutant(dna));
	}



}
