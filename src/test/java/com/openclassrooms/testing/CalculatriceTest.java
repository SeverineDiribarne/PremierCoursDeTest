package com.openclassrooms.testing;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTest {

	private Calculatrice1 calculatorUnderTest;

	private static Instant startedAt;

	@BeforeAll
	public static void initStartingTime() {
		System.out.println("Appel avant tous les tests");
		startedAt = Instant.now();
	}

	@AfterAll
	public static void showTestDuration() {
		System.out.println("Appel après tous les tests");
		Instant endedAt = Instant.now();
		long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));

	}

	@BeforeEach
	public void initCalculator() {
		calculatorUnderTest = new Calculatrice1();
		System.out.println("Appel avant chaque test");
	}

	@AfterEach
	public void undefCalculator() {
		System.out.println("Appel après chaque test");
		calculatorUnderTest = null;
	}

	@Test
	void testAddTwoPositiveNumbers() {
		// ARRANGE
		int a = 2;
		int b = 3;

		// ACT
		int somme = calculatorUnderTest.add(a, b);

		// ASSERT
		assertThat(somme).isEqualTo(5);
	}

	@Test
	void testMultiply_shouldReturnTheProduct_ofTwoIntegers() {
		// ARRANGE
		int a = 42;
		int b = 11;
		// ACT
		int produit = calculatorUnderTest.multiply(a, b);
		// ASSERT
		assertThat(produit).isEqualTo(462);
	}

	@ParameterizedTest(name = "{0} x 0 doit être égal à 0")
	@ValueSource(ints = { 1, 2, 42, 1001, 5089 })
	public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int arg) {
		// ARRANGE - Tout est prêt !

		// ACT -- Multiplier par zéro
		int actualResult = calculatorUnderTest.multiply(arg, 0);

		// ASSERT -- Ca vaut toujours zéro !
		assertThat(actualResult).isEqualTo(0);
	}

	@ParameterizedTest(name = "{0} + {1} doit être égal à {2}")
	@CsvSource({ "1,1,2", "2,3,5", "42,57,99" })
	public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectResult) {
		// ARRANGE - Tout est prêt !

		// ACT
		int actualResult = calculatorUnderTest.add(arg1, arg2);
		// ASSERT
		assertThat(actualResult).isEqualTo(expectResult);
	}

	@Test
	@Timeout(1)
	public void longCalcul_shouldComputeInLessThan1Second() {
		// ARRANGE

		// ACT
		calculatorUnderTest.longCalculation();

		// ASSERT
		// ...
	}

	@Test
	public void listDigits_shouldReturnsTheListOfDigits_ofPositiveInteger() {
		// GIVEN
		int number = 95897;
		// WHEN
		Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

		// THEN
//		Set<Integer> expectedDigits = Stream.of(5, 7, 8, 9).collect(Collectors.toSet());
//		assertEquals(expectedDigits, actualDigits);
		assertThat(actualDigits).containsExactlyInAnyOrder(9, 5, 8, 7);
	}

	@Test
	public void listDigits_shouldReturnsTheListOfDigits_ofNegativeInteger() {
		// GIVEN
		int number = -124432;
		// WHEN
		Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

		// THEN
		assertThat(actualDigits).containsExactlyInAnyOrder(1, 2, 3, 4);
	}

	@Test
	public void listDigits_shouldReturnsTheListOfZero_ofZero() {
		// GIVEN
		int number = 0;
		// WHEN
		Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

		// THEN
		assertThat(actualDigits).containsExactly(0);
	}

}
