package raspry_alarm;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
	private int currentSecond;
	private Calendar calendar;

	static AlarmClock alarm;

	tt sd;

	public static void main(String[] args) {
		Clock clock = new Clock();
		clock.start();
	}

	public Clock() {
		alarm = new AlarmClock();
		sd = new tt();
	}

	private void reset() {
		calendar = Calendar.getInstance();
		currentSecond = calendar.get(Calendar.SECOND);
		alarm.setTime(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
		alarm.cheak();
	}

	public void start() {
		reset();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (currentSecond == 60) {
					reset();
				}

				setTime(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), currentSecond,
						calendar.get(Calendar.AM_PM));
				currentSecond++;
			}
		}, 0, 1000);
	}

	public void setTime(int h, int m, int s, int i) {

		sd.c.settime(h, m, s);

		sd.c.repaint();
	}
}