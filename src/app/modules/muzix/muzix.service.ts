import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Track } from './track';
import { USER_NAME } from '../authentication/authenticationservice.service';

@Injectable({
  providedIn: 'root'
})
export class MuzixService {
  thirdPartyApi: string;
  thirdPartyKey: string;
  springEndPoint: string;
  username: string;

  constructor(private httpClient: HttpClient) {
    this.thirdPartyApi = 'http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=';
    this.thirdPartyKey = '&api_key=573bc5ead37a2703cd8f0d4a64f21937&format=json';
    //this.springEndPoint = 'http://localhost:8081/api/v1/muzixservice/';
    //this.springEndPoint = 'http://localhost:8089/api/v1/usertrackservice/';
    this.springEndPoint = 'http://localhost:8881/usertrackservice/api/v1/usertrackservice/';
   }

   getTrackDetails(countryName: string): Observable<any>{
    const url = `${this.thirdPartyApi}${countryName}${this.thirdPartyKey}`;
    return this.httpClient.get(url);
   }

   addTrackToWishList(track: Track){
     this.username = sessionStorage.getItem(USER_NAME);
     const url = this.springEndPoint + "user/" + this.username + "/track";
     console.log("New Url: " + url);
     return this.httpClient.post(url, track, {
       observe: "response"
     });
   }

   getAllTracksForWishlist(): Observable<Track[]>{
     this.username = sessionStorage.getItem(USER_NAME);
     const url = this.springEndPoint + "user/" + this.username + "/tracks";
     return this.httpClient.get<Track[]>(url);
   }

   deleteTrackFromWishList(track: Track){
     this.username = sessionStorage.getItem(USER_NAME);
     const url = this.springEndPoint + "user/" + this.username + "/track";
     const options = {
       headers: new HttpHeaders({
         'Content-Type': 'application/json'
       }),
       body: track
     };
     console.log("In Delete: ", track);
     return this.httpClient.delete(url, options);
   }

   updateComments(track){
     this.username = sessionStorage.getItem(USER_NAME);
     const url = this.springEndPoint + "user/" + this.username + "/track";
     return this.httpClient.patch(url, track, {observe: "response"});
   }

   filterArtists(tracks: Array<Track>, artistName: string){
     const results = tracks.filter(track=>{
       return track.artist.name.match(artistName);
     });
     console.log("Filtered Data: ", results);
     return results;
   }
}
