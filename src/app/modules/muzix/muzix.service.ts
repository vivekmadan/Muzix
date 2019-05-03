import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Track } from './track';

@Injectable({
  providedIn: 'root'
})
export class MuzixService {
  thirdPartyApi: string;
  thirdPartyKey: string;
  springEndPoint: string;

  constructor(private httpClient: HttpClient) {
    this.thirdPartyApi = 'http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=';
    this.thirdPartyKey = '&api_key=573bc5ead37a2703cd8f0d4a64f21937&format=json';
    this.springEndPoint = 'http://localhost:8081/api/v1/muzixservice/';
   }

   getTrackDetails(countryName: string): Observable<any>{
    const url = `${this.thirdPartyApi}${countryName}${this.thirdPartyKey}`;
    return this.httpClient.get(url);
   }

   addTrackToWishList(track: Track){
     const url = this.springEndPoint + "track";
     return this.httpClient.post(url, track, {
       observe: "response"
     });
   }

   getAllTracksForWishlist(): Observable<Track[]>{
     const url = this.springEndPoint + "tracks";
     return this.httpClient.get<Track[]>(url);
   }

   deleteTrackFromWishList(track){
     const url = this.springEndPoint + "track/" + `${track.trackId}`;
     return this.httpClient.delete(url, {responseType: "text"});
   }

   updateComments(track){
     const url = this.springEndPoint + "track/" + `${track.trackId}`;
     return this.httpClient.put(url, track, {observe: "response"});
   }
}
