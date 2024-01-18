package main.clone;

public class Person implements Cloneable {
    public int age;
    public Body body;

    @Override
    public Object clone() {
        try {
            Person clone = (Person) super.clone();
            if (body != null) {
                clone.body = (Body) body.clone();
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
