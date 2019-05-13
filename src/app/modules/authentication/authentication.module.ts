import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './components/register/register.component';
import { AngularmaterialModule } from '../angularmaterial/angularmaterial.module';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { AppRoutingModule } from '../../app-routing.module';
@NgModule({
  declarations: [RegisterComponent, LoginComponent],
  imports: [
    CommonModule,
    AngularmaterialModule,
    FormsModule,
    AppRoutingModule
  ],
  exports: [
    AngularmaterialModule,
    AppRoutingModule
  ]
})
export class AuthenticationModule { }
