package scratch;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AssertTest {

	private Account account;

	@Before
	public void createAccount() {
		account = new Account("an account name");
	}

	@Test
	public void hasPositiveBalance() {
		account.deposit(50);
		assertTrue(account.hasPositiveBalance());
	}

	@Test
	public void depositIncreasesBalance() {
		int initialBalance = account.getBalance();
		account.deposit(100);
		assertTrue(account.getBalance() > initialBalance);
		assertTrue(account.getBalance(), equalTo(100));

	}
}
