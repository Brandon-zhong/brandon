package functionprograming;

/**
 * @author brandon
 * create on 2019-06-05
 * desc: java 8 新特性，函数式编程 ，定义一个的接口并用@FunctionalInterface注解修饰
 * 接口内有且仅有一个抽象方法，所有符合抽象方法模式的方法都可以用这个接口来接收，如下栗子
 */
public class App {

    public static void main(String[] args) {

        FunctionConvert<String, String> convert = FunctionDemo::getString;
        System.out.println(convert.convert("this is data"));

        FunctionConvert<String, FunctionDemo> demo = FunctionDemo::new;
        FunctionDemo data = demo.convert("data");

    }

}
