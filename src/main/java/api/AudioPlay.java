package api;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class AudioPlay {
    public static void generateSpeech(String text)
    {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        try {
            voice.allocate();
            voice.speak(text);
        } catch (Exception e1) {
            System.out.println("Error audio!");
        }
    }
}