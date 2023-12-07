package ie.atu.dip;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


public class InsuranceProgramTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	//test cases 
    @ParameterizedTest
    
    @MethodSource("calculateTotalInsuranceInput")
    void testCalculateTotalInsurance(int age, int accidents, int expectedTotalInsurance) {
        int actualTotalInsurance = InsuranceProgram.calculateTotalInsurance(age, accidents);
        assertEquals(expectedTotalInsurance, actualTotalInsurance);
    }
	 static Stream<Arguments> calculateTotalInsuranceInput() {
	        return Stream.of(
	                Arguments.of(20, 1, 650),
	                Arguments.of(35, 10, 0),
	                Arguments.of(30, 5, 1075),
	                Arguments.of(23, 0, 600)
	        );
	    }

 
	@Test@Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
	public void testCalculateTotalInsurance_YoungDriverWithOneAccident() {
	  
        int age = 20;
        int accidents = 1;
        int expectedTotalInsurance = 650; // Basic insurance (500) + Surcharge (100) + One accident surcharge (50)

        int actualTotalInsurance = InsuranceProgram.calculateTotalInsurance(age, accidents);

        Assertions.assertEquals(expectedTotalInsurance, actualTotalInsurance);
    }
    

    @Test@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void testCalculateTotalInsurance_OlderDriverWithManyAccidents() {
        int age = 35;
        int accidents = 10; // More accidents than allowed with surcharge
        int expectedTotalInsurance = 0; // No insurance

        int actualTotalInsurance = InsuranceProgram.calculateTotalInsurance(age, accidents);

        Assertions.assertEquals(expectedTotalInsurance, actualTotalInsurance);
    }

    @Test@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void testCalculateTotalInsurance_YoungDriverWithNoAccidents() {
        int age = 23;
        int accidents = 0;
        int expectedTotalInsurance = 600; // Basic insurance (500) + Surcharge (100) + No accident surcharge

        int actualTotalInsurance = InsuranceProgram.calculateTotalInsurance(age, accidents);

        Assertions.assertEquals(expectedTotalInsurance, actualTotalInsurance);
    }

    @Test@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void testCalculateTotalInsurance_OlderDriverWithFiveAccidents() {
        int age = 30;
        int accidents = 5;
        int expectedTotalInsurance = 1075; // Basic insurance (500) + No surcharge (since 5 accidents) + No accident surcharge

        int actualTotalInsurance = InsuranceProgram.calculateTotalInsurance(age, accidents);

        Assertions.assertEquals(expectedTotalInsurance, actualTotalInsurance);
    }

   @Test@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)  
   
    public void testaddedCPA_TwoAccidents() {
        int accidents = 2;
        int expectedSurcharge = 125; // Surcharge for two accidents

        int actualSurcharge = InsuranceProgram.addedCPA(accidents);

        Assertions.assertEquals(expectedSurcharge, actualSurcharge);
    }
   
    @Test@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void testaddedCPA_MoreThanSixAccidents() {
        int accidents = 8; // More accidents than allowed with surcharge
        int expectedSurcharge = 0; // No surcharge, should return 0

        int actualSurcharge = InsuranceProgram.addedCPA(accidents);

        Assertions.assertEquals(expectedSurcharge, actualSurcharge);
    }
    @Test@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void out() {
        System.out.print("hello");
        Assertions.assertEquals("hello", outContent.toString());
    }

    @Test@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void err() {
        System.err.print("hello again");
        Assertions.assertEquals("hello again", errContent.toString());
    }
    @AfterAll@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public static void testsCompleted() throws IOException  {
    	System.out.println("All tests are done!");
    	//clean up the environment 
    	FileOutputStream f = new FileOutputStream("file.txt");

        System.setOut(new PrintStream(f));
        java.util.Date date = new java.util.Date();    
        System.out.println("tests completed: " + date);   
        f.flush();
        f.close();
        System.out.println("Running Garbage Collector...");
        Runtime.getRuntime().gc();
        System.out.println("Completed.");
       
     }
    @BeforeEach@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void setUpStreams() {
    	
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent)); 
    }
    @Test@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)			
    public void m7() {					
        System.out.println("Using @Test@timeout,it can be used to enforce timeout in JUnit5 test case");					
    }		

    @AfterEach@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    
    
   @BeforeAll@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
   public static void checkStatusTest() {
    	System.out.println("Tests Starting");
    	
    } 
  }

