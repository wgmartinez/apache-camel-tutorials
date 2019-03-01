package co.wgmartinez.spring.integration;

public class MyHelloService implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
