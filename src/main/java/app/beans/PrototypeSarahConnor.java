package app.beans;

import app.utils.InjectRandomInt;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Data
@Component
public class PrototypeSarahConnor implements Speaker, DisposableBean {

	private String status = "Alive";
	@InjectRandomInt(year = 2020)
	private int year;

	@Override
	public String saySomething() {
		return "No fate in year " + year;
	}

	@SuppressWarnings("RedundantThrows")
	@Override
	public void destroy() throws Exception {
		status = "Died";
	}
}
