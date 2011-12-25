

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

public class OptionsScreen extends Form {
	private final TextField pomodoroMinsTextField;
	private final TextField shortBreakMinsTextField;
	private final TextField longBreakMinsTextField;
	private final TextField pomodoroCountsTextFields;
	
	private final OptionsStore store;

	public OptionsScreen(String title, OptionsStore store) {
		super(title);
		this.store = store;
		pomodoroMinsTextField = new TextField("Pomodoro", String.valueOf(store.getPomodoroMins()),
				3, TextField.NUMERIC);
		shortBreakMinsTextField = new TextField("Short Break", String.valueOf(store.getShortBreakMins()),
				3, TextField.NUMERIC);
		longBreakMinsTextField = new TextField("Long Break", String.valueOf(store.getLongBreakMins()),
				3, TextField.NUMERIC);
		pomodoroCountsTextFields = new TextField("Sets", String.valueOf(store.getPomodoroCounts()),
				1, TextField.NUMERIC);
		
		append(pomodoroMinsTextField);
		append(shortBreakMinsTextField);
		append(longBreakMinsTextField);
		append(pomodoroCountsTextFields);
	}

	public int getPomodoroMins() {
		return Integer.parseInt(pomodoroMinsTextField.getString());
	}

	public int getShortBreakMins() {
		return Integer.parseInt(shortBreakMinsTextField.getString());
	}

	public int getLongBreakMins() {
		return Integer.parseInt(longBreakMinsTextField.getString());
	}

	public int getPomodoroCounts() {
		return Integer.parseInt(pomodoroCountsTextFields.getString());
	}
	
	public void refresh() {
		pomodoroMinsTextField.setString(String.valueOf(store.getPomodoroMins()));
		shortBreakMinsTextField.setString(String.valueOf(store.getShortBreakMins()));
		longBreakMinsTextField.setString(String.valueOf(store.getLongBreakMins()));
		pomodoroCountsTextFields.setString(String.valueOf(store.getPomodoroCounts()));
	}
}
