package main.clone;

/**
 * 拷贝机制源于Java中移除了指针的概念，增加了引用指代指向的地址
 */
public class Test {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.age = 1;
        person1.body = new Body();
        person1.body.footCount = 2;

        Person person2 = person1;
        // 对象不是同一个对象，引用是相同的
        System.out.println("person1 = " + person1 + " person2 = " + person2);

        Person clone1 = (Person) person1.clone();
        System.out.println("clone1 = " + clone1 + " age = " + clone1.age);

        // 浅拷贝，Person中的引用类型（Body）引用没变
//        System.out.println("person1.body = " + person1.body + " clone1.body = " + clone1.body);

        // 深拷贝
        System.out.println("person1.body = " + person1.body + " clone1.body = " + clone1.body);
    }
}
