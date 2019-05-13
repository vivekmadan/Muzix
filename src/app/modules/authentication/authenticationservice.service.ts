import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
export const USER_NAME = "username";
export const TOKEN_NAME = "token";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationserviceService {
private SpringRegisterEndPoinst: string;
private SpringSaveEndPoint: string;

  constructor(private httpClient: HttpClient) {
    this.SpringRegisterEndPoinst = "http://localhost:8081/api/v1/usertrackservice/";
    this.SpringSaveEndPoint = "http://localhost:8083/api/v1/userservice/";
   }

   registerUser(newUser){
     const url  = this.SpringRegisterEndPoinst + "register";
     return this.httpClient.post(url, newUser, {observe: "response"});
   }

   saveUser(newUser){
     const saveUrl = this.SpringSaveEndPoint + "save";
     return this.httpClient.post(saveUrl, newUser, {observe: "response"});
    }

    loginUser(newUser){
      const url = this.SpringSaveEndPoint + "login";
      sessionStorage.setItem(USER_NAME, newUser.username);
      return this.httpClient.post(url, newUser, {observe : "response"});
    }

    getToken(){
      return localStorage.getItem(TOKEN_NAME);
    }

    isTokenExpired(token?: string): boolean{
      if(localStorage.getItem(TOKEN_NAME)){
        return true;
      }else{
        return false;
      }
    }

    logout(){
      sessionStorage.removeItem(USER_NAME);
      sessionStorage.clear();
      localStorage.removeItem(TOKEN_NAME);
      localStorage.clear();
      console.log("Logged Out");
    }
}
