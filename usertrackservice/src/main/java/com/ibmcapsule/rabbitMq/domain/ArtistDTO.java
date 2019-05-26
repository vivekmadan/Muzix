package com.ibmcapsule.rabbitMq.domain;

public class ArtistDTO {

  private String artistId;

  private String artistName;

  private String url;

  private ImageDTO image;

  public ArtistDTO() {
  }

  public ArtistDTO(String artistId, String artistName, String url, ImageDTO image) {
    this.artistId = artistId;
    this.artistName = artistName;
    this.url = url;
    this.image = image;
  }

  public String getArtistId() {
    return artistId;
  }

  public void setArtistId(String artistId) {
    this.artistId = artistId;
  }

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ImageDTO getImage() {
    return image;
  }

  public void setImage(ImageDTO image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "ArtistDTO{" +
      "artistId='" + artistId + '\'' +
      ", artistName='" + artistName + '\'' +
      ", url='" + url + '\'' +
      ", image=" + image +
      '}';
  }
}
