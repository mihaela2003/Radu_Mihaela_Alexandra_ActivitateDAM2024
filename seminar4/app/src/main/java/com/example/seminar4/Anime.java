package com.example.seminar4;

public class Anime {
    public String denumire;
    public int anAparitie;
    public String genre;
    public boolean finished;
    public int nrEpisoade;

    public Anime(String denumire, int anAparitie, String genre, boolean finished, int nrEpisoade) {
        this.denumire = denumire;
        this.anAparitie = anAparitie;
        this.genre = genre;
        this.finished = finished;
        this.nrEpisoade = nrEpisoade;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getAnAparitie() {
        return anAparitie;
    }

    public void setAnAparitie(int anAparitie) {
        this.anAparitie = anAparitie;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getNrEpisoade() {
        return nrEpisoade;
    }

    public void setNrEpisoade(int nrEpisoade) {
        this.nrEpisoade = nrEpisoade;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Anime{");
        sb.append("denumire='").append(denumire).append('\'');
        sb.append(", anAparitie=").append(anAparitie);
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", finished=").append(finished);
        sb.append(", nrEpisoade=").append(nrEpisoade);
        sb.append('}');
        return sb.toString();
    }
}
