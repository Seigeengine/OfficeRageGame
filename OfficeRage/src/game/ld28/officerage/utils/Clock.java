package game.ld28.officerage.utils;

import java.util.HashMap;
import java.util.Map;

public class Clock {
    private static Map<String, Timer> timers = new HashMap<String, Timer>();
    private static long lastDeltaRequest = -1;
    private static int deltaCounter = 0;
    private static long lastFPS = -1;
    private static int FPS = -1;
    private static int syncFPS = -1;

    private Clock() {
    }

    public static long getTime() {
        return System.currentTimeMillis();
    }

    public static int getDelta() {
        deltaCounter++;

        long time = getTime();
        int delta = (int) (lastDeltaRequest == -1 ? 0 : time - lastDeltaRequest);
        lastDeltaRequest = time;

        if (lastFPS == -1) {
            lastFPS = time;
        } else if (time - 1000 > lastFPS) {
            lastFPS = time;
            FPS = deltaCounter;
            deltaCounter = 0;
            System.out.println("FPS: " + FPS);
        }

        return delta;
    }

    public static void setSyncFPS(int fps) {
        syncFPS = fps;
    }

    public static int getSyncTime() {
        long time = System.currentTimeMillis();
        int delayFromLastFrame = Math.round(1000f / syncFPS);
        return (int) (delayFromLastFrame - (time - lastDeltaRequest));
    }

    public static void registerTimer(String name) {
        timers.put(name.intern(), new Timer());
    }

    public static void unregisterTimer(String name) {
        if (timers.containsKey(name)) {
            timers.remove(name);
        } else {
            throw new IllegalArgumentException(
                    "Attempted to unregister a timer that wasn't registered.");
        }

    }

    public static Timer getTimer(String name) {
        if (timers.containsKey(name)) {
            return timers.get(name);
        }
        throw new IllegalArgumentException(
                "Attmpted to get an unregistered timer.");
    }

    public static class Timer {
        private long startTime;
        private long stopTime;

        public void start() {
            startTime = getTime();
        }

        public void stop() {
            stopTime = getTime();
        }

        public int getElapsedTime() {
            if (stopTime != 0) {
                return (int) (stopTime - startTime);
            } else if (startTime != 0) {
                return (int) (getTime() - startTime);
            }
            throw new IllegalStateException("");
        }
    }
}