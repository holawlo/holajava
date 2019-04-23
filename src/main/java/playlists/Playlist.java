package playlists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static playlists.PlayMode.*;

public class Playlist extends Playable {

    private List<Playable> playables = new ArrayList<>();
    private PlayMode playMode;

    public Playlist(PlayMode playMode) {
        this.playMode = playMode;
    }

    protected void addToPlaylist(Playable playable){
        playables.add(playable);
    }

    @Override
    protected String play() {
        if (playMode == SEQUENTIAL) {
            return collectTitles(playables);
        } else if (playMode == LOOP) {
            String result = "";
            for (int i = 0; i < 10; i++) {
                result = result + collectTitles(playables);
            }
            return result;
        } else if (playMode == RANDOM){
            List<Playable> tempList = new ArrayList<>(playables);
            Collections.shuffle(tempList);
            return collectTitles(tempList);
        }
        return "ERROR!!";
    }

    private String collectTitles(List<Playable> playables) {
        return playables.stream()
                .map(Playable::play)
                .collect(Collectors.joining("\n"));
    }
}
