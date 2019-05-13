import { Component, OnInit } from '@angular/core';
import { AuthenticationserviceService } from 'src/app/modules/authentication/authenticationservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private authService: AuthenticationserviceService,
    private route: Router) { }

  ngOnInit() {
  }

  logout(){
    this.authService.logout();
    console.log("Logout called.");
    this.route.navigate(["/login"]);
  }

}
