package iloveyouboss;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProfileTest {

	// 나쁜 테스트 코드 1 : 테스트의 이름이 어떠한 테스트 인지 알 수 없음
	@Test
	public void test() {
		Profile profile = new Profile("Bull Hockey, Inc");
		Question question = new BooleanQuestion(1, "Got bonuses?");

		// add
		Answer profileAnswer = new Answer(question, Bool.FALSE);
		profile.add(profileAnswer);

		Criteria criteria = new Criteria();
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
		criteria.add(criterion);
	}

	// 테스트는 이름의 테스트 내용을 명시해야 한다.
	@Test
	public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
		Profile profile = new Profile("Bull Hockey, Inc");
		Question question = new BooleanQuestion(1, "Got bonuses?");

		// add
		Answer profileAnswer = new Answer(question, Bool.FALSE);
		profile.add(profileAnswer);

		Criteria criteria = new Criteria();
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
		criteria.add(criterion);

		boolean matches = profile.matches(criteria);
		assertFalse(matches);
	}

	// 비슷한 내용의 테스트지만 중복코드가 많다.
	@Test
	public void matchAnswersTrueForAnyDontCareCriteria() {
		Profile profile = new Profile("Bull Hockey, Inc");
		Question question = new BooleanQuestion(1, "Got milk?");

		Answer profilerAnswer = new Answer(question, Bool.FALSE);
		Criteria criteria = new Criteria();
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);
		criteria.add(criterion);

		boolean matches = profile.matches(criteria);

		assertTrue(matches);
	}
}
