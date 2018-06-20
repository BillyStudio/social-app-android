package com.socialapp.wsd.models;

public class UserAccountSettings {
    private long followers;
    private long following;
    private String motto;
    private long posts;
    private String profile_photo;
    private String username;

    public UserAccountSettings(long followers, long following, String motto, long posts, String profile_photo, String username) {
        this.followers = followers;
        this.following = following;
        this.motto = motto;
        this.posts = posts;
        this.profile_photo = profile_photo;
        this.username = username;
    }
    public UserAccountSettings() {
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserAccountSettings{" +
                "followers=" + followers +
                ", following=" + following +
                ", motto='" + motto + '\'' +
                ", posts=" + posts +
                ", profile_photo='" + profile_photo + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
