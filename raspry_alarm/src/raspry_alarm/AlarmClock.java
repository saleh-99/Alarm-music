package raspry_alarm;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AlarmClock {

	private int currentHour;
	private int currentMinute;
	private int currentSecond;
	private ArrayList<Integer> alarmHour;
	private ArrayList<Integer> alarmMinute;

	private ArrayList<Boolean> alarmOn;

	public AlarmClock() {
		alarmHour = new ArrayList<Integer>();
		alarmMinute = new ArrayList<Integer>();
		alarmOn = new ArrayList<Boolean>();

	}

	public void setAlarm(boolean alarm, int hour, int minute) {

		alarmHour.add(hour);
		alarmMinute.add(minute);
		alarmOn.add(alarm);

	}

	public void turnOffAlarm(int alarm) {
		alarmOn.set(alarm, false);
	}

	public void turnOnAlarm(int alarm) {
		alarmOn.set(alarm, true);
	}

	public void setTime(int hour, int minute, int second) {
		currentHour = hour;
		currentMinute = minute;
		currentSecond = second;
		second = currentSecond;
	}
	
	public int geti(int h,int m) {
		for (int j = 0; j < alarmHour.size(); j++) {
			if(alarmHour.get(j)==h && alarmMinute.get(j)==m) {
				return j;
			}
		}
		
		return -1;
		
	}

	public void cheak() {
		System.out.println("cheak");

		if (alarmHour.contains(currentHour) && alarmMinute.contains(currentMinute)) {
			if (alarmOn.get(geti(currentHour,currentMinute))) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {

							String s = choose(new File("s.txt"));

							System.out.println(s);
							Desktop.getDesktop().browse(new URI(s));
						} catch (IOException | URISyntaxException e) {

							e.printStackTrace();
						}
					}
				}).start();

			}
		}
	}

	public static String choose(File f) throws FileNotFoundException {
		String result = null;
		Random rand = new Random();
		int n = 0;
		for (Scanner sc = new Scanner(f); sc.hasNext();) {
			n++;
			String line = sc.nextLine();
			if (rand.nextInt(n) == 0)
				result = line;
		}

		return result;
	}

}