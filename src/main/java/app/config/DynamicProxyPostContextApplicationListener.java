package app.config;

import app.utils.AfterContextUpInitMethod;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Data
@Component
public class DynamicProxyPostContextApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	private final ConfigurableListableBeanFactory factory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition definition = factory.getBeanDefinition(beanDefinitionName);
			String beanClassName = definition.getBeanClassName();

			try {
				Class<?> originalClass = Class.forName(beanClassName);
				for (Method method : originalClass.getMethods()) {
					if (method.isAnnotationPresent(AfterContextUpInitMethod.class)) {
						Object bean = applicationContext.getBean(beanDefinitionName);
						method.invoke(bean);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
