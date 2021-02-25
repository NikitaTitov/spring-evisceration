package app.beans;

import app.utils.AfterContextUpInitMethod;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
public class PhaseClass {

	private boolean isFirstConstructorPassed;
	private boolean isSecondConstructorPassed;
	private boolean isThirdConstructorPassed;

	public PhaseClass() {
		isFirstConstructorPassed = true;
	}

	@PostConstruct
	public void init() {
		isSecondConstructorPassed = true;
	}

	@SuppressWarnings("unused")
	@AfterContextUpInitMethod
	public void lastInit() {
		isThirdConstructorPassed = true;
	}

}
