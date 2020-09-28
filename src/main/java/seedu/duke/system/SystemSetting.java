package seedu.duke.system;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import seedu.duke.ui.gui.MainStage;

import static seedu.duke.common.Constant.DEFAULT_SYSTEM_MUSIC;

public class SystemSetting {
    private static MediaView backgroundMusicView;

    /**
     * Initialises the system setting
     * Currently only the language is implemented
     * Sets the system language to English
     */
    public static void initialise(){
        setBackgroundMediaView(DEFAULT_SYSTEM_MUSIC, 0.1, true);
        MainStage.setBgmView(backgroundMusicView);
    }

    /**
     * Returns the background music generated
     */
    public static void setBackgroundMediaView(String bgm, double volume, boolean autoplay) {
        Media backgroundMusic = new Media(MainStage.class.getResource(bgm).toExternalForm());
        MediaPlayer backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        setBackgroundMusic(backgroundMusicPlayer, volume, autoplay);
        //***************** loop (repeat) the music  ******************
        backgroundMusicPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                backgroundMusicPlayer.seek(Duration.ZERO);
            }
        });
        backgroundMusicView = new MediaView(backgroundMusicPlayer);
        MainStage.setBgmView(backgroundMusicView);
    }

    /**
     * Sets the property of the background music
     * @param backgroundMusicPlayer the background music
     */
    private static void setBackgroundMusic(MediaPlayer backgroundMusicPlayer, double volume, boolean autoplay) {
        backgroundMusicPlayer.setAutoPlay(autoplay);
        backgroundMusicPlayer.setVolume(volume);
    }

    public static MediaView getBackgroundMusicView() {
        return backgroundMusicView;
    }

}
