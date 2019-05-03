import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardcontainerComponent } from './components/cardcontainer/cardcontainer.component';
import { CardComponent } from './components/card/card.component';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {AppRoutingModule} from '../../app-routing.module';
import { WishlistComponent } from './components/wishlist/wishlist.component';
import { FooterComponent } from './components/footer/footer.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { DialogComponent } from './components/dialog/dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import {FormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';

@NgModule({
  declarations: [CardcontainerComponent, CardComponent, HeaderComponent, WishlistComponent, FooterComponent, DialogComponent],
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatMenuModule,
    AppRoutingModule,
    MatSnackBarModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    MatFormFieldModule
  ],
  entryComponents: [
    DialogComponent
  ],
  exports: [CardcontainerComponent, 
    HeaderComponent, 
    AppRoutingModule, 
    WishlistComponent,
    FooterComponent]
})
export class MuzixModule { }
