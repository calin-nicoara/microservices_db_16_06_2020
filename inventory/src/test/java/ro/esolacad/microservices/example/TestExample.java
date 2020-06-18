package ro.esolacad.microservices.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExample {

    @Test
    public void testInitialJunit() {
        Calculator calculator = new Calculator();

        int add = calculator.add(2, 3);

        Assertions.assertEquals(5, add);
    }
}
