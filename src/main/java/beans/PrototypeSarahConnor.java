package beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component()
public class PrototypeSarahConnor implements Speaker, DisposableBean {
	@Override
	public String saySomething() {
		return "No fate";
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Sarah die!");
	}
}
