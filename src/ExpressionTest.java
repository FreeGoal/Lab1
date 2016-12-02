import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExpressionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testSimplify() {
		Expression smpl = new Expression();
		smpl.TermNumber = 1;
		smpl.VariableNumber = 3;
		smpl.Variable.add('x');
		smpl.Variable.add('y');
		smpl.Variable.add('z');
		smpl.Factor[0] = 3;
		smpl.Factor[1] = 2;
		smpl.VariableCount[0][0] = 1;
		smpl.VariableCount[0][1] = 2;
		smpl.VariableCount[1][0] = 1;
		smpl.VariableCount[2][0] = 1;
		
		String str = "!simplify x=4";
		Expression exp = new Expression();
		exp = exp.simplify(str, smpl);
		OutControl.showexpr(exp);
		
		assertEquals(0,exp.VariableCount[0][0]);
		assertEquals(0,exp.VariableCount[0][1]);
		assertEquals(1,exp.VariableCount[1][0]);
		assertEquals(1,exp.VariableCount[2][0]);		
		assertEquals(12,exp.Factor[0]);
		assertEquals(32,exp.Factor[1]);
		assertEquals(3,exp.VariableNumber);
		assertEquals(1,exp.TermNumber);

		//fail("Not yet implemented"); // TODO
	}

}
