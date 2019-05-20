import { Component, OnInit } from '@angular/core';
import { User } from '../../User';
import { AuthenticationserviceService } from '../../authenticationservice.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User;

  constructor(
    private authService: AuthenticationserviceService, 
    private snackBar: MatSnackBar,
    private router: Router) {
    this.user = new User();
   }

  ngOnInit() {
  }

  register(){
    console.log(this.user);
    this.authService.registerUser(this.user).subscribe(data=>{
      if(data.status === 201){
        this.snackBar.open("Successfully Registered", "", {duration: 1000});
        // this.authService.saveUser(this.user).subscribe(saveData=>{
        //   console.log("Save Data: " + saveData);
        // });

        this.router.navigate(["/login"]);
      }
    },
    error=>{
      console.log(error);
    });
  }

}
