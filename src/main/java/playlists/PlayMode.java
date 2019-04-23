package playlists;

public enum PlayMode {

    SEQUENTIAL("normalnie"), LOOP("pętla"), RANDOM("losowo");

    private String plName;

    PlayMode(String plName) {
        this.plName = plName;
    }
}
