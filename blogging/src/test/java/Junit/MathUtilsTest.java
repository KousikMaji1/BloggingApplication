package Junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("When running MathUtils")
class MathUtilsTest {

    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;
    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        mathUtils = new MathUtils();
    }

    @Nested
    @DisplayName("add method")
    @Tag("Math")
    class AddTest {
        @Test
        @DisplayName("When adding 2 positive number")
        void testAddingTwoPositives() {
            assertEquals(2, mathUtils.add(1, 1),
                    "Add method should return the sum of two numbers");
        }

        @Test
        @DisplayName("When adding two negative numbers")
        void testAddingTwoNegatives() {
            int expected = -2;
            int actual=mathUtils.add(-1,-1);
            assertEquals(expected, actual,
                    ()->"Add method should return "+expected+" the sum of two numbers "+actual);
        }

        @Test
        @DisplayName("When adding one negative and one positive number")
        void testAddingAPositiveAndANegative() {
            assertEquals(0, mathUtils.add(-1, 1),
                    "Add method should return the sum of two numbers");
        }
    }

    @Test
    @Tag("Math")
    @DisplayName("testing add method")
    void testAdd(){
        int expected =2;
        int reality =mathUtils.add(1,1);
        assertEquals(expected,reality,"The add method should add two numbers");
    }
    @Test
    //@EnabledOnOs(OS.MAC)
    @Tag("Math")
    void testDivide(){
        boolean isServerUp= true;
        assumeTrue(isServerUp);
        assertThrows(ArithmeticException.class,()->mathUtils.divide(10,
                0),"Divide by zero should throw");
    }

    @Test
    @Tag("Math")
    void testMultiply(){
        testReporter.publishEntry("Running "+testInfo.getDisplayName() + "with Tags " +testInfo.getTags());
        assertAll(
                ()->assertEquals(4,mathUtils.multiply(2,2)),
                ()->assertEquals(0,mathUtils.multiply(0,2)),
                ()->assertEquals(-4,mathUtils.multiply(-2,2))
                );
    }

    @RepeatedTest(3)
    @Tag("Circle")
    void testComputeCircleArea(){
        assertEquals(314.1592653589793, mathUtils.computeCircleArea(10) , "Should return right " +
                "circle area");
    }

    @Test
    @Disabled
    @DisplayName("TDD method. should not run")
    void testDisabled(){
        fail("this test should be disabled");
    }

}