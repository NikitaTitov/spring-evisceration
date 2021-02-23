package beans;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Data
@Component
public class SingleLoraPalmer implements Speaker, DisposableBean {

	private String status = "Alive";
	@Override
	public String saySomething() {
		return "I know who kill me";
	}

	@Override
	public void destroy() throws Exception {
		status = "Died";
	}
}
