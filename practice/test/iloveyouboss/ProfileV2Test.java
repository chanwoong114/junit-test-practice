package iloveyouboss;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfileV2Test {

	private Profile profile;
	private BooleanQuestion question;
	private Criteria criteria;

	// Before 어노테이션을 통해서 테스트 메서드에서 중복되는 코드들을 제거 가능하다.
	@Before
	public void create() {
		profile = new Profile("Bull Hockey, Inc");
		question = new BooleanQuestion(1, "Got bonuses?");
		criteria = new Criteria();
	}

	/**
	 * Junit 이 matchAnswersFalseWhenMustMatchCriteriaNotMet 메서드를 먼저 실행할 경우 작동 순서
	 * 1. @Before 메서드를 호출하여 profile, question, criteria 변수를 적절한 인스턴스로 초기화한다.
	 * 2. matchAnswersFalseWhenMustMatchCriteriaNotMet 메서드를 실행하고 테스트가 통과 혹은 실패했는지 확인한다.
	 * 3. 다른 테스트가 있기 때문에 Junit 은 ProfileTest 인스턴스를 새롭게 생성한다.
	 * 4. Junit 은 새로운 인스턴스에 대해 @Before 메서드를 호출하여 필드를 초기화 한다.
	 * 5. Junit 은 matchAnswersFalseWhenMustMatchCriteriaNotMet 라는 다른 메서드를 호출한다.
	 *
	 * 이와 같은 방법으로 Junit 은 테스트를 독립적으로 실행한다.
	 */

	// 인라인으로 코드 길이 축약
	@Test
	public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
		profile.add(new Answer(question, Bool.FALSE));
		criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

		boolean matches = profile.matches(criteria);
		assertFalse(matches);
	}

	@Test
	public void matchAnswersTrueForAnyDontCareCriteria() {
		profile.add(new Answer(question, Bool.FALSE));
		criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

		boolean matches = profile.matches(criteria);

		assertTrue(matches);
	}
}
