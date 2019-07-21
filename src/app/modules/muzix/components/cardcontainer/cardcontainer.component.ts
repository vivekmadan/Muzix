import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Track } from '../../track';
import { Artist } from '../../artist';
import { Image } from '../../image';
import { MuzixService } from '../../muzix.service';
import {ActivatedRoute} from '@angular/router';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-cardcontainer',
  templateUrl: './cardcontainer.component.html',
  styleUrls: ['./cardcontainer.component.css']
})
export class CardcontainerComponent implements OnInit {
  tracks: Array<Track>;
  trackObj: Track;
  artistObj: Artist;
  imageObj: Image;
  countryName: string;
  id: number;
  statusCode: number;
  errorStatus: string;
  artistName: string;
  searchTracks: Array<Track>;

  constructor(private muzixService: MuzixService,22
    private routes: ActivatedRoute,
    private matSnackBar: MatSnackBar) {
    this.tracks = [];
   }

  ngOnInit() {
    const tempData = this.routes.data.subscribe(newdata => {
      this.countryName = newdata.country;
      console.log('Country Name: ', this.countryName);
    });

    this.muzixService.getTrackDetails(this.countryName).subscribe(tracks => {
      console.log(tracks);
      const data = tracks['tracks']['track'];
      this.id = 0;
      this.tracks = [];
      data.forEach(targetData => {
        this.id ++;
        this.artistObj = new Artist();
        this.artistObj = targetData["artist"];

        this.imageObj = new Image();
        this.imageObj.text = targetData["image"][2]["#text"];
        this.imageObj.url = targetData["image"][2]["size"];

        this.trackObj = new Track();
        this.trackObj = targetData;
        this.artistObj.image = this.imageObj;
        this.trackObj.artist = this.artistObj;
        this.trackObj.trackId = this.countryName.slice(0,3) + this.id;
        this.tracks.push(this.trackObj);
      });
      this.searchTracks = this.tracks;
    });
  }

  addToWishList(track){
    console.log('Card Container: ', track);
    this.muzixService.addTrackToWishList(track).subscribe(data =>{
      console.log(data);
      this.statusCode = data.status;
      if(this.statusCode === 201){
        console.log('Success', this.statusCode);
        this.matSnackBar.open("Track successfull added !!!","",{
          duration: 1000
        });
      }
    },

    error => {
      this.errorStatus = `${error.status}`;
      const errorMsg = `${error.error.message}`;
      this.statusCode = parseInt(this.errorStatus, 10);

      if(this.statusCode === 409){
        this.matSnackBar.open(errorMsg, "",{
          duration: 1000
        });
        this.statusCode = 0;
      }
    });
  }

  onKey(event: any){
    this.artistName = event.target.value;
    console.log("Artist Name: ", this.artistName);
    const result = this.searchTracks.filter(track => {
      return track.artist.name.match(this.artistName);
    });
    console.log("Filtered Data: ", result);
    this.tracks = result;
  }

}
