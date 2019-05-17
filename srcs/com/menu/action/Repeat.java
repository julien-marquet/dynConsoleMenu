package	com.menu.action;

public class Repeat extends Action {
	public Repeat() {
		this.name = "repeat";
	}
	public Repeat(String name) {
		this.name = name;
	}
	public void execute() {
		System.out.println("Repeat");
	}
}