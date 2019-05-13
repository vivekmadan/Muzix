import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardcontainerComponent } from './components/cardcontainer/cardcontainer.component';
import { CardComponent } from './components/card/card.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import { AppRoutingModule } from '../../app-routing.module';
import { WishlistComponent } from './components/wishlist/wishlist.component';
import { FooterComponent } from './components/footer/footer.component';
import { DialogComponent } from './components/dialog/dialog.component';
import { FormsModule } from '@angular/forms';
import { AngularmaterialModule } from '../angularmaterial/angularmaterial.module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from './interceptor.service';
import { MuzixService } from './muzix.service';


@NgModule({
  declarations: [CardcontainerComponent, CardComponent, HeaderComponent, WishlistComponent, FooterComponent, DialogComponent],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    AngularmaterialModule
  ],
  entryComponents: [
    DialogComponent
  ],
  exports: [CardcontainerComponent,
    HeaderComponent,
    AppRoutingModule,
    WishlistComponent,
    FooterComponent
  ],
  providers: [
    MuzixService, {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }
  ]
})
export class MuzixModule { }
