package app.config;

import app.utils.InjectRandomInt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.stream.Stream;

@Component
public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Stream
				.of(bean.getClass().getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(InjectRandomInt.class))
				.forEach(field -> {
					field.setAccessible(true);
					InjectRandomInt injectRandomInt = field.getAnnotation(InjectRandomInt.class);
					ReflectionUtils.setField(field, bean, injectRandomInt.year());
				});

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}
}
