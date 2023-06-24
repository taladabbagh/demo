package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ExampleClassTest {
    @Test
    public void testExampleClass() {
        // Create a mock of ExampleClass
        ExampleClass exampleMock = Mockito.mock(ExampleClass.class);

        // Set up the mock behavior
        when(exampleMock.getMessage()).thenReturn("Mocked message");

        // Call the method on the mock object
        String message = exampleMock.getMessage();

        // Assert the expected result
        assertEquals("Mocked message", message);
    }
}
