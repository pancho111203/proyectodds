package auxiliar;

import static org.junit.Assert.assertEquals;
import game.input.Gamepad;

import org.junit.Test;

public class junit {
	
	@Test
	public void testLargest() {
		assertEquals(Gamepad.getSingleton(),true);
	}

}
