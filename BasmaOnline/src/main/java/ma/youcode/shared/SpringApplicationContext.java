
package ma.youcode.shared;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {
	private static ApplicationContext CONTEXT;

	@Override
	// Contexte est un mécanisme qui ma ne permettre instancié ou récupère un objet
	// dans application
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CONTEXT = applicationContext;
	}

	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}

}
