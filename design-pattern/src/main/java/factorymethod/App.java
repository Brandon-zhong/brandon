package factorymethod;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author brandon
 * Created on 2019-06-19.
 * desc:
 */
public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:factorymethod/app.xml");
        Object random = context.getBean("random");
        System.out.println(random.toString());
    }
}
