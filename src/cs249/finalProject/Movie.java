package cs249.finalProject;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {
    private Integer Id;
    private String Title;
    private String Description;
    private java.util.Date ReleaseDate;
    private Integer VoteCount;
    private Double VoteAverage;
    private Double Popularity;

    public Movie(String title, String description, Integer id) {

    }

    public Movie() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }

    public Integer getVoteCount() {
        return VoteCount;
    }

    public void setVoteCount(Integer voteCount) {
        VoteCount = voteCount;
    }

    public Double getVoteAverage() {
        return VoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        VoteAverage = voteAverage;
    }

    public Double getPopularity() {
        return Popularity;
    }

    public void setPopularity(Double popularity) {
        Popularity = popularity;
    }
}
