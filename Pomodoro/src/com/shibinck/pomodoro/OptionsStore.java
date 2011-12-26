package com.shibinck.pomodoro;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class OptionsStore {
	private RecordStore recordStore;
	private int pomodoroMins;
	private int shortBreakMins;
	private int longBreakMins;
	private int pomodoroCounts;
	
	public int getPomodoroMins() {
		return pomodoroMins;
	}

	public int getShortBreakMins() {
		return shortBreakMins;
	}

	public int getLongBreakMins() {
		return longBreakMins;
	}

	public int getPomodoroCounts() {
		return pomodoroCounts;
	}

	public OptionsStore() {
		try {
			recordStore = RecordStore.openRecordStore("options", true);
			if (recordStore.getNumRecords() == 0) {
				saveOptions(25, 5, 15, 4);
			}
			loadOptions();
		} catch (RecordStoreFullException e) {
			e.printStackTrace();
		} catch (RecordStoreNotFoundException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	private void loadDefaults() {
		pomodoroMins = 25;
		shortBreakMins = 5;
		longBreakMins = 15;
		pomodoroCounts = 4;
	}

	private void loadOptions() {
		boolean loaded = false;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(recordStore.getRecord(1));
			DataInputStream inputStream = new DataInputStream(bais);
			pomodoroMins = inputStream.readInt();
			shortBreakMins = inputStream.readInt();
			longBreakMins = inputStream.readInt();
			pomodoroCounts = inputStream.readInt();
			loaded = true;
		} catch (RecordStoreNotOpenException e) {
			e.printStackTrace();
		} catch (InvalidRecordIDException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		} catch (EOFException eofe) {
			eofe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!loaded) loadDefaults();
	}

	public void saveOptions(int pomodoroMins, int shortBreakMins,
			int longBreakMins, int pomodoroCounts) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(baos);
		try {
			outputStream.writeInt(pomodoroMins);
			outputStream.writeInt(shortBreakMins);
			outputStream.writeInt(longBreakMins);
			outputStream.writeInt(pomodoroCounts);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		byte[] b = baos.toByteArray();
		try {
			if (recordStore.getNumRecords() == 0) {
				recordStore.addRecord(b,  0, b.length);
			} else {
				recordStore.setRecord(1, b, 0, b.length);
			}
		} catch (RecordStoreException rse) {
			rse.printStackTrace();
		}
		loadOptions();
	}
}
