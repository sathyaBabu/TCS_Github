package com.sathya.tcs_github.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
"name": "Sathya Babu ",
  "company": "Edureka",
  "blog": "http://www.edureka.co",
  "location": "Bangalore",

  "bio": "Been in IT for over two decades, Consulting, Development, and corporate training.",
 "avatar_url": "https://avatars.githubusercontent.com/u/7117192?v=4"
 */
public class GithubUser {


    private String name ;
    private String company;
    private String blog ;
    private String bio ;
    // BUG here...    Right var : avatar_url
    // Lets fix the bug
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


}
/*
{
  "login": "sathyaBabu",
  "id": 7117192,
  "node_id": "MDQ6VXNlcjcxMTcxOTI=",
  "avatar_url": "https://avatars.githubusercontent.com/u/7117192?v=4",
  "gravatar_id": "",
  "url": "https://api.github.com/users/sathyaBabu",
  "html_url": "https://github.com/sathyaBabu",
  "followers_url": "https://api.github.com/users/sathyaBabu/followers",
  "following_url": "https://api.github.com/users/sathyaBabu/following{/other_user}",
  "gists_url": "https://api.github.com/users/sathyaBabu/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/sathyaBabu/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/sathyaBabu/subscriptions",
  "organizations_url": "https://api.github.com/users/sathyaBabu/orgs",
  "repos_url": "https://api.github.com/users/sathyaBabu/repos",
  "events_url": "https://api.github.com/users/sathyaBabu/events{/privacy}",
  "received_events_url": "https://api.github.com/users/sathyaBabu/received_events",
  "type": "User",
  "site_admin": false,
  "name": "Sathya Babu ",
  "company": "Edureka",
  "blog": "http://www.edureka.co",
  "location": "Bangalore",
  "email": null,
  "hireable": true,
  "bio": "Been in IT for over two decades, Consulting, Development, and corporate training.",
  "twitter_username": null,
  "public_repos": 103,
  "public_gists": 0,
  "followers": 13,
  "following": 1,
  "created_at": "2014-03-31T15:31:14Z",
  "updated_at": "2022-03-02T02:02:14Z"
}
 */