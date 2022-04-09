/*
 * v0.1
 * Lestra. 
 */

package reminder;

import javax.swing.JFrame;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.time.LocalDateTime;

public class Reminder {
	public static void main (String args[]) {
		//Создаем главный фрэйм программы
		JFrame frame = new JFrame("Simple Reminder");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.ICONIFIED); //сразу скрыть прогу в трэе
		
		//Создаем доступ к оповещениям в трэе
		SystemTray tray = SystemTray.getSystemTray();

	    Image image = Toolkit.getDefaultToolkit().createImage("");
	    TrayIcon trayIcon = new TrayIcon(image);
	    trayIcon.setImageAutoSize(true);
	    trayIcon.setToolTip("Reminder");
	    try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Главный цикл, проверяющий сколько сейчас времени
		while (true) {
			LocalDateTime now = LocalDateTime.now();
			int hours = now.getHour();
			int minutes = now.getMinute();
			int seconds = now.getSecond();
			
			//Усыпляем цикл, чтобы он запускался не чаще чем раз в 1 с.
			long millis = System.currentTimeMillis();
			try {
				Thread.sleep(1000 - millis % 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Выводим напоминалки
			if (hours > 8 && hours < 17) {
				if (minutes == 25) {
					trayIcon.displayMessage("Reminder", "Take a little break", TrayIcon.MessageType.INFO);
				} else if (minutes == 50) {
					trayIcon.displayMessage("Reminder", "Breath in 4, Hold 4, Breath out 4, Hold 4", TrayIcon.MessageType.INFO);
				} else if (minutes == 15) {
					trayIcon.displayMessage("Reminder", "Change your sitting pose", TrayIcon.MessageType.INFO);
				}
			}
		}//while
	}//main
}//class


