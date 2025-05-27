package org.example.backend.dto.movie_details;

import java.util.List;

public class UserStatusDTO {
    private Boolean watched;
    private Boolean wishlisted;
    private float rating;
    private List<String> comment;

    // Getters and Setters
    public Boolean getWatched() {
        return watched;
    }

    public void setWatched(Boolean watched) {
        this.watched = watched;
    }

    public Boolean getWishlisted() {
        return wishlisted;
    }

    public void setWishlisted(Boolean wishlisted) {
        this.wishlisted = wishlisted;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }
}