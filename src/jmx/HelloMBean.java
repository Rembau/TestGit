package jmx;

public interface HelloMBean {
    public String getBody();
    public void setBody(String name);
    public void printHello();
    public void printHello(String whoName);
}
