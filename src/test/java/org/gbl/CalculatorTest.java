package org.gbl;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void calculate() {
        assertThat(calculator.calculate("1+2")).isEqualTo(3);
        assertThat(calculator.calculate("4 + 2")).isEqualTo(6);
        assertThat(calculator.calculate("(4 + 2)")).isEqualTo(6);
        assertThat(calculator.calculate("(4 + 2) * 4")).isEqualTo(24);
        assertThat(calculator.calculate("4 + 2 * 4")).isEqualTo(12);
        assertThat(calculator.calculate("2 + 3 * 4")).isEqualTo(14);
        assertThat(calculator.calculate("(2 + 3) * 4")).isEqualTo(20);
        assertThat(calculator.calculate("10 + 5")).isEqualTo(15);
        assertThat(calculator.calculate("10 + 25")).isEqualTo(35);
        assertThat(calculator.calculate("158 + 155")).isEqualTo(313);
        assertThat(calculator.calculate("12 + 155 * 2 - 10")).isEqualTo(312);
    }

    @Test
    void sum() {
        assertThat(calculator.sum(4, 3)).isEqualTo(7);
        assertThat(calculator.sum(1.1, 1.2)).isEqualTo(2.3);
    }

    @Test
    void subtract() {
        assertThat(calculator.subtract(2, 1)).isEqualTo(1);
        assertThat(calculator.subtract(1.1, 1.0)).isEqualTo(0.1);
    }

    @Test
    void multiply() {
        assertThat(calculator.multiply(1, 2)).isEqualTo(2);
        assertThat(calculator.multiply(3, 3)).isEqualTo(9);
        assertThat(calculator.multiply(1.1, 2.0)).isEqualTo(2.2);
    }

    @Test
    void divide() {
        assertThat(calculator.divide(1, 2)).isEqualTo(0.5);
        assertThat(calculator.divide(1.5, 2.5)).isEqualTo(0.6);
    }
}