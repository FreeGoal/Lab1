import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExpressionDrvtvTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testDerivative() {
		Expression smpl = new Expression();
		smpl.TermNumber = 1;
		smpl.VariableNumber = 1;
		smpl.Variable.add('x');
		smpl.Factor[0] = 2;
		smpl.Factor[1] = 3;
		smpl.VariableCount[0][0] = 2;
		smpl.VariableCount[0][1] = 1;
		
		String str = "!d/d x";
		Expression exp = new Expression();
		exp = exp.derivative(str, smpl);
		exp.showexpr(exp);
		
		assertEquals(1,exp.VariableCount[0][0]);
		assertEquals(0,exp.VariableCount[0][1]);
		assertEquals(4,exp.Factor[0]);
		assertEquals(3,exp.Factor[1]);
		assertEquals(1,exp.VariableNumber);
		assertEquals(1,exp.TermNumber);
		//fail("Not yet implemented"); // TODO
	}
}
