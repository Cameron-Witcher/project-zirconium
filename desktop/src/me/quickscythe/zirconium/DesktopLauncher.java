package me.quickscythe.zirconium;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import me.quickscythe.zirconium.Game;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(160);
		config.setTitle("Project Zirconium");
		config.setIdleFPS(5);

		int mon_id = 0;
		if (arg.length > 0) {
			mon_id = Integer.parseInt(arg[0]);
		}

		int width = 1280;
		int height = 720;
		Game wrapper = new Game();
		Graphics.Monitor monitor;
		try{
			monitor = Lwjgl3ApplicationConfiguration.getMonitors()[mon_id];
		}catch(ArrayIndexOutOfBoundsException ex){
			monitor = Lwjgl3ApplicationConfiguration.getMonitors()[0];
		}

		Graphics.DisplayMode mode = Lwjgl3ApplicationConfiguration.getDisplayMode(monitor);
		int posX = monitor.virtualX + mode.width / 2 - width / 2;
		int posY = monitor.virtualY + mode.height / 2 - height / 2;

		config.setWindowedMode(width, height);
		config.setWindowPosition(posX, posY);
		config.setWindowSizeLimits(0, 0, width, height);
		config.setWindowIcon("badlogic.jpg");
		new Lwjgl3Application(wrapper, config);
	}
}
