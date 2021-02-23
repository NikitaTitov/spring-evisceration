import beans.PrototypeSarahConnor;
import beans.SingleLoraPalmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MainTest {


	private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

	@BeforeEach
	public void init() {
		context.scan("beans"); // We can setup path to scan packages where spring must search beans
		context.registerBean(PrototypeSarahConnor.class, bd -> bd.setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)); // Or we can register
		context.refresh(); //After adding new beans we must refresh context
	}

	@Test
	public void createAppContext() {
		SingleLoraPalmer loraPalmer = context.getBean(SingleLoraPalmer.class);
		PrototypeSarahConnor sarahConnor = context.getBean(PrototypeSarahConnor.class);
		assertEquals(loraPalmer.saySomething(), "I know who kill me");
		assertEquals(sarahConnor.saySomething(), "No fate in year 2020");
		context.destroy();
		assertEquals(loraPalmer.getStatus(), "Died");
		assertEquals(sarahConnor.getStatus(), "Alive");
	}

}