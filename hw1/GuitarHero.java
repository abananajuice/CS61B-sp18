import synthesizer.GuitarString;

import java.util.HashMap;

public class GuitarHero {

    private static final double CONCERT_A = 440.0;
    //    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    // get dict [key --> GuiterString]
    private static java.util.HashMap<Character, synthesizer.GuitarString> getdict(String keys) {
        java.util.HashMap<Character, synthesizer.GuitarString> Gs = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            char key = keyboard.charAt(i);
            synthesizer.GuitarString value = new GuitarString(CONCERT_A * Math.pow(2, (i - 24) / 12.0));
            Gs.put(key, value);
        }

        return Gs;

    }

    private static double getSample(java.util.HashMap<Character, synthesizer.GuitarString> g) {
        double s = 0;
        for (int i = 0; i < keyboard.length(); i++) {
            s = g.get(keyboard.charAt(i)).sample() + s;
        }
        return s;
    }

    private static void ticAll(java.util.HashMap<Character, synthesizer.GuitarString> g) {

        for (int i = 0; i < keyboard.length(); i++) {
            g.get(keyboard.charAt(i)).tic();
        }
    }

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        java.util.HashMap<Character, synthesizer.GuitarString> sounds = getdict(keyboard);


        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (sounds.containsKey(key)) {
                    sounds.get(key).pluck();
                }

            }

            double sample = getSample(sounds);

            StdAudio.play(sample);

            ticAll(sounds);
        }
    }
}
