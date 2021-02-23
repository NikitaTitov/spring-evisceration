package beans;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import utils.InjectRandomInt;

@Data
@Component()
public class PrototypeSarahConnor implements Speaker, DisposableBean {

	private String status = "Alive";
	@InjectRandomInt(year = 2020)
	private int year;
	@Override
	public String saySomething() {
		return "No fate in year " + year;
	}

	@Override
	public void destroy() throws Exception {
		status = "Died";
	}
}
