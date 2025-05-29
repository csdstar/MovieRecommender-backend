package org.example.backend.dto.movie_details;

public class RatingDTO {
    private Float score;
    private Integer votes;

    // Getters and Setters
    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}