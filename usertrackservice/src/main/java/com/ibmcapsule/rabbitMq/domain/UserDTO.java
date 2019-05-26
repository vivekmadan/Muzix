package com.ibmcapsule.rabbitMq.domain;

import java.util.List;

public class UserDTO {

  private String username;
  private String email;
  private String password;
  private List<TrackDTO> trackList;

  public UserDTO() {
  }

  public UserDTO(String username, String email, List<TrackDTO> trackList) {
    this.username = username;
    this.email = email;
    this.trackList = trackList;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<TrackDTO> getTrackList() {
    return trackList;
  }

  public void setTrackList(List<TrackDTO> trackList) {
    this.trackList = trackList;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserDTO{" +
      "username='" + username + '\'' +
      ", email='" + email + '\'' +
      ", trackList=" + trackList +
      '}';
  }
}
