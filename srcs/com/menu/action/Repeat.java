package	com.menu.action;
import com.menu.action.actionparam.*;

public class Repeat extends Action {
	public Repeat(RepeatActionParam actionParam) {
		super(actionParam);
	}
	public void execute() {
		System.out.println("Repeat");
	}
}