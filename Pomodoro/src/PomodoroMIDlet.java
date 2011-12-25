


//import java.util.Timer;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;



public class PomodoroMIDlet extends MIDlet implements CommandListener {
	private static final int POMODORO_STATE = 0;
	private static final int SHORT_BREAK_STATE = 1;
	private static final int LONG_BREAK_STATE = 2;
	private static final int STOP_STATE = 3;
	
	private final Command startStopCommand = new Command("Start", Command.OK, 1);
	private final Command exitCommand = new Command("Exit", Command.EXIT, 1);
	private final Command optionsCommand = new Command("Options", Command.SCREEN, 1);
	private final Command saveCommand = new Command("Save", Command.OK, 1);
	private final Command backCommand = new Command("Back", Command.BACK, 1);
	private OptionsScreen optionsScreen;
	private TimerScreen timerScreen;
	private OptionsStore store;
	
	//private final Timer timer = new Timer();
	private int state;
	private int pomodoroCount;

	public PomodoroMIDlet() {
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

	protected void startApp() throws MIDletStateChangeException {
		store = new OptionsStore();
		optionsScreen = new OptionsScreen("Options", store);
		timerScreen = new TimerScreen("Pomodoro");
		optionsScreen.addCommand(saveCommand);
		optionsScreen.addCommand(backCommand);
		timerScreen.addCommand(optionsCommand);
		timerScreen.addCommand(startStopCommand);
		timerScreen.addCommand(exitCommand);
		optionsScreen.setCommandListener(this);
		timerScreen.setCommandListener(this);
		state = STOP_STATE;
		pomodoroCount = 0;
		Display.getDisplay(this).setCurrent(optionsScreen);
	}
	
	public void commandAction(Command command, Displayable displayable) {
		if (command == exitCommand) {
			notifyDestroyed();
		} else if (command == backCommand) {
			Display.getDisplay(this).setCurrent(timerScreen);
		} else if (command == optionsCommand) {
			optionsScreen.refresh();
			Display.getDisplay(this).setCurrent(optionsScreen);
		} else if (command == saveCommand) {
			store.saveOptions(optionsScreen.getPomodoroMins(), 
					optionsScreen.getShortBreakMins(),
					optionsScreen.getLongBreakMins(),
					optionsScreen.getPomodoroCounts());
			Display.getDisplay(this).setCurrent(timerScreen);
		} else if (command == startStopCommand) {
			
		}
	}
}