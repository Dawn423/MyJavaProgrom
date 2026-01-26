package week1;

public interface HelloWorld {
    void printHelloWorld();
}

class HelloWorldImpl implements HelloWorld {
    @Override
    public void printHelloWorld() {
        System.out.println("Hello World");
    }
}

class HelloWorldTest {
    public static void main(String[] args) {
        HelloWorldImpl helloWorld = new HelloWorldImpl();
        helloWorld.printHelloWorld();
    }
}