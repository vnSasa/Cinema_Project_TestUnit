package _1._Unit;

public class InputMenuString {
	
	boolean flag = false;
	private String string;

	public InputMenuString(String string) {
		super();
		if ((string.hashCode() >= 49 && string.hashCode() <= 55) || string.equals("q") || string.equals("menu")) {
			this.string = string;
			flag = true;
		} else {
			try {
				throw new Exception("Wrong choise, try again[1-9]...");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				Main.Menu2();
				flag = false;
			}
		}
	}

	public String getString() {
		return string;
	}
}
