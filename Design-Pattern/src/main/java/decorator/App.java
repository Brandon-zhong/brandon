package decorator;


/**
 * @author brandon
 * create on 2019-06-10
 * desc: 装饰器模式，用于在不破外修改原类的基础上对原类进行加强，提供了灵活的替代扩展功能。
 * 虽然类的继承也能达到相同的效果，但是限于java的单继承模式，如果都用了继承就先知了子类继承其他的，
 * 这样是不切实际的，在遇到大量独立扩展时会产生大量的子类来支持每种组合
 */
public class App {

    public static void main(String[] args) {

        //创建长剑
        Sword longSword = new LongSword();
        longSword.attack();

        //创建暴风大剑
        Sword stormSword = new StormSword(longSword);
        stormSword.attack();
    }

}
