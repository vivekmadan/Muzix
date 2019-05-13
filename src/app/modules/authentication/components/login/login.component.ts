import { Component, OnInit } from '@angular/core';
import { User } from '../../User';
import { Router } from '@angular/router';
import { AuthenticationserviceService } from '../../authenticationservice.service';
import { MatSnackBar } from '@angular/material';
export const TOKEN_NAME = "token";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User;
  constructor(
    private authService: AuthenticationserviceService,
    private snackBar: MatSnackBar,
    private route: Router) {
    this.user = new User();
   }

  ngOnInit() {
  }

  login(){
    this.authService.loginUser(this.user).subscribe(data=>{
      console.log(data);
      if(data){
        console.log("Token coming: " + data.body["token"]);
        localStorage.setItem(TOKEN_NAME, data.body["token"]);
        this.snackBar.open(data.body["message"], "", {duration: 1000});
        this.route.navigate(["/India"]);
      }
    },
    error=>{
      console.log("error:", error);
      if(error.status === 404){
        const errorMessage = error.error.message;
        this.snackBar.open(errorMessage, "", {duration: 1000});
      }
    });

  }

}
