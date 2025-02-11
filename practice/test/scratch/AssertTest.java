package scratch;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

		// assertThat 단언은 명확한 값을 비교한다
		// 실패한다면 java.lang.AssertionError 과 같은 스택 트레이스를 출력한다.
		assertThat(account.getBalance(), equalTo(101));
	}

	@Test
	public void depositIncreasesBalance_hamcrestAssertTrue() {
		account.deposit(50);
		System.out.println(is(true));
		assertThat(account.getBalance() > 0, is(true));
	}

	@Ignore
	@ExpectToFail
	@Test
	public void comparesArrayFailing() {
		assertThat(new String[] {"a", "b", "c"}, equalTo(new String[] {"a", "b"}));
	}

	@Test
	public void comparesArraysPassing() {
		assertThat(new String[] {"a", "b"}, equalTo(new String[] {"a", "b"}));
	}

	@Ignore
	@ExpectToFail
	@Test
	public void comparesCollectionsFailing() {
		assertThat(Arrays.asList(new String[] {"a"}),
			equalTo(Arrays.asList(new String[] {"a", "ab"})));
	}

	@Test
	public void comparesCollectionsPassing() {
		assertThat(Arrays.asList(new String[] {"a"}),
			equalTo(Arrays.asList(new String[] {"a"})));
	}

	// JUnit 햄크레스트 매쳐를 이용하면 다음과 같은 일을 할 수 있다.
	// - 객체 타입을 검사한다.
	// - 두 객체의 참조가 같은 인스턴스인지 검사한다.
	// - 다수의 매처를 결합하여 둘 다 혹은 둘 중에 어떤 것이든 성공하는지 검사한다.
	// - 어떤 컬렉션이 요소를 포함하거나 조건에 부합하는지 검사한다.
	// - 어떤 컬렉션이 아이템 몇 개를 모두 포함하는지 검사한다.
	// - 어떤 컬렉션에 있는 모든 요소가 매처를 준수하는지 검사한다.
	// 이외에도 많은 일들을 할 수 있다.

	@Test
	public void testWithWorthlessAssertionComment() {
		account.deposit(50);
		// 모든 JUnit 단언의 형식에는 message 라는 선택적 첫 번째 인자가 있다.
		// message 인자는 단어의 근거를 설명해 준다.
		// 하지만 가장 좋은 방법은 테스트를 코드 만으로 이해하게 작성하는 것이다.
			// 테스트 이름을 변경하거나,
			// 의미 있는 상수를 도입하거나
			// 변수 이름을 개선하거나
			// 복잡한 초기화 작업을 의미 있는 이름을 가진 도우미 메서드로 추출하거나,
			// 가독성이 우수한 햄크레스트 단언을 사용하는 등의 방법을 활용하는 것이 테스트를 훨씬 좋게 만든다.
		// 단언 메시지는 테스트가 실패할 경우 유용한 정보를 좀 더 빠르게 알려주지만 군더더기 없는 테스트 코드를 만드는 것과 상충된다.
		assertThat("account balance is 100", account.getBalance(), equalTo(50));
	}

	// 예외를 기대하는 3가지 방법
	// 1번째, 단순한 방식 애너테이션 사용
	@Test(expected = InsufficientFundsException.class)
	public void throwsWhenWithdrawingTooMuch() {
		account.withdraw(50);
	}

	// 2번째, 옛 방식: try/catch 와 fail
	@Test
	public void throwsWhenWithDrawingTooMuchTry() {
		try {
			account.withdraw(50);
			fail();
		}
		catch (InsufficientFundsException e) {
			assertThat(e.getMessage(), containsString("balance only : 0"));
		}
	}

	// 3번째, 새로운 방식 : ExpectedException 규칙
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void exceptionRule() {
		thrown.expect(InsufficientFundsException.class);
		thrown.expectMessage("balance only : 0");

		account.withdraw(50);
	}

	// 테스트 코드에 try/catch 블록을 넣지 말고 그 대신 발생하는 예외를 던지자
	// 정말 예외적인 상황을 제외하고는 예외가 발생하지 않는다.
	// 아주 드문 경우로, 기대하지 않은 예외가 발생하더라도 JUnit 이 나머지 일을 대신해준다.
	// JUnit 은 예외를 잡아 테스트 실패가 아니라 테스트 오류로 보고한다.
	@Test
	public void readsFromTestFile() throws IOException {
		String filename = "test.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write("test data");
		writer.close();
		// .. 이어질 테스트 코드
	}

}
