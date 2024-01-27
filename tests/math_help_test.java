import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class math_help_test {

    @Test
    public void testShortestAngleDifference() {
        assertEquals(0, MathHelp.shortestAngleDifference(0, 0), 0);
        assertEquals(Math.PI / 2, MathHelp.shortestAngleDifference(0, Math.PI / 2), 0);
        assertEquals(-Math.PI / 2, MathHelp.shortestAngleDifference(0, -Math.PI / 2), 0);
        assertEquals(0, MathHelp.shortestAngleDifference(0, Math.PI * 2), 0);
        assertEquals(0, MathHelp.shortestAngleDifference(0, -Math.PI * 2), 0);
        assertEquals(Math.PI / 2, MathHelp.shortestAngleDifference(Math.PI / 2, Math.PI), 0);
        assertEquals(Math.PI / 2, MathHelp.shortestAngleDifference(Math.PI / 2, -Math.PI), 0);
        assertEquals(0, MathHelp.shortestAngleDifference(Math.PI / 2, Math.PI / 2), 0);
        assertEquals(-Math.PI, MathHelp.shortestAngleDifference(Math.PI / 2, -Math.PI / 2), 0);
        assertEquals(-Math.PI / 2, MathHelp.shortestAngleDifference(Math.PI / 2, Math.PI * 2), 0);
        assertEquals(-Math.PI / 2, MathHelp.shortestAngleDifference(Math.PI / 2, -Math.PI * 2), 0);
        assertEquals(-Math.PI / 2, MathHelp.shortestAngleDifference(-Math.PI / 2, Math.PI), 0);
        assertEquals(-Math.PI / 2, MathHelp.shortestAngleDifference(-Math.PI / 2, -Math.PI), 0);
        assertEquals(-Math.PI, MathHelp.shortestAngleDifference(-Math.PI / 2, Math.PI / 2), 0);
        assertEquals(0, MathHelp.shortestAngleDifference(-Math.PI / 2, -Math.PI / 2), 0);
        assertEquals(Math.PI / 2, MathHelp.shortestAngleDifference(-Math.PI / 2, Math.PI * 2), 0);
        assertEquals(Math.PI / 2, MathHelp.shortestAngleDifference(-Math.PI / 2, -Math.PI * 2), 0);
        assertEquals(0, MathHelp.shortestAngleDifference(Math.PI, Math.PI), 0);
        assertEquals(0, MathHelp.shortestAngleDifference(Math.PI, -Math.PI), 0);
        assertEquals(-Math.PI / 2, MathHelp.shortestAngleDifference(Math.PI, Math.PI / 2), 0);
        assertEquals(-Math.PI, MathHelp.shortestAngleDifference(0, Math.PI), 0);
    }
}
