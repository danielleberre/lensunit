package org.lensunit;

public class Basic extends TestCase {

    public void testTrivialOk() {
        // an empty test trivially passes
    }

    public void testEasyCases() {
        assertTrue(2 < 3);
        assertFalse(2 > 3);
        assertEquals(new Integer(2), new Integer(2));
    }

    public void testFailingTest() {
        assertTrue(4 < 3);
    }

    public void testFailingEquals() {
        assertEquals(2, 4);
    }

    public void testError() {
        throw new NullPointerException();
    }

    public void testIntegerValueOf() {
        Integer a = Integer.valueOf(10);
        Integer b = Integer.valueOf(10);
        Integer c = new Integer(10);
        Integer d = new Integer(10);
        assertEquals(a, b);
        assertEquals(a, c);
        assertEquals(a, d);
        assertSame(a, b);
        assertNotSame(a, c);
        assertNotSame(a, d);
        assertNotSame(b, c);
        assertNotSame(b, d);
        assertNotSame(c, d);
    }

    public void testDouble() {
        Double d1 = Double.valueOf(1.0);
        Double d2 = Double.valueOf(2.0);
        Double epsilon = 0.0001;
        assertEquals(d2, 2 * d1, epsilon);
    }

    public void testFloat() {
        Float d1 = Float.valueOf(1.f);
        Float d2 = Float.valueOf(2.0f);
        Float epsilon = 0.0001f;
        assertEquals(d2, 2 * d1, epsilon);
    }

    public void testInt() {
        assertEquals(2, 2 * 1);
    }

    public void testString() {
        assertEquals("aaabbb", "aaa" + "bbb");
    }

    public void testAssumption() {
        assumeTrue(false, "J'arrête le test");
        fail("Mon test échoue ?");
    }

    public void testTimeout() {
        assertTimeout(1000, () -> {
            // do nothing
        });
    }

    public void testFailingTimeout() {
        assertTimeout(1000, () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // do nothing
            }
        });
    }
}
