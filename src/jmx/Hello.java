package jmx;

public class Hello implements HelloMBean {
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String name) {
		this.body = name;
	}

	public void printHello() {
		System.out.println("Hello World, " + body);
	}

	public void printHello(String whoName) {
		System.out.println("Hello , " + whoName);
	}
}