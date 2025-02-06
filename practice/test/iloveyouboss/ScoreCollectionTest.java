package iloveyouboss;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.Test;

public class ScoreCollectionTest {
	@Test
	public void answerArithmeticMeanOfTwoNumbers() {
		// Arrange
		ScoreCollection collection = new ScoreCollection();
		collection.add(() -> 5);
		collection.add(() -> 7);

		// Act
		int actualResult = collection.arithmeticMean();

		// Assert
		assertThat(actualResult, equalTo(6));
	}

}
