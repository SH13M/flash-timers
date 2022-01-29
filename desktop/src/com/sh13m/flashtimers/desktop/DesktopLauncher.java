package com.sh13m.flashtimers.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sh13m.flashtimers.FlashTimers;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Flash Timers";
		config.width = 640;
		config.height = 480;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		config.addIcon("iconL.png", Files.FileType.Internal); // Mac
		config.addIcon("iconM.png", Files.FileType.Internal); // Windows + Linux
		config.addIcon("iconS.png", Files.FileType.Internal); // Windows
		new LwjglApplication(new FlashTimers(), config);
	}
}
