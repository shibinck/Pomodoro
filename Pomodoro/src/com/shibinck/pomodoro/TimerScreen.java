package com.shibinck.pomodoro;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

public class TimerScreen extends Form {
	private final TextField timeTextField;
	public TimerScreen(String title) {
		super(title);
		timeTextField = new TextField("Time", "", 256, TextField.ANY | TextField.UNEDITABLE);
		append(timeTextField);
		clear();
	}
	public void update(int minsLeft, int pomodoroCount, int state) {
		String stateString = state == PomodoroMIDlet.POMODORO_STATE? "Pomodoro": 
			state == PomodoroMIDlet.SHORT_BREAK_STATE? "Short Break": "Long Break";
		timeTextField.setString("Mins Left: " + minsLeft + "\nPomodoro Count: " + 
			pomodoroCount + "\nState: " + stateString);
	}
	public void clear() {
		timeTextField.setString("Stopped");
	}
}
