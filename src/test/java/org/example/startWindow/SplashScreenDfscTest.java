package org.example.startWindow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SplashScreenDfscTest {

    @Test
    void testSplashScreenWindow() {
        SplashScreenDfsc splashScreen = new SplashScreenDfsc();
        SplashScreenDfsc.splashScreenWindow();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertFalse(splashScreen.isVisible());
    }
}