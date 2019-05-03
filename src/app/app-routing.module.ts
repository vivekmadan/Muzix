import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CardcontainerComponent } from './modules/muzix/components/cardcontainer/cardcontainer.component';
import { WishlistComponent } from './modules/muzix/components/wishlist/wishlist.component';

const routes: Routes = [
  {
    path: "",
    component: CardcontainerComponent,
    data: {country : "Australia"}
  },
  {
    path: "India",
    component: CardcontainerComponent,
    data: {country : "India"}
  },
  {
    path: "Spain",
    component: CardcontainerComponent,
    data: {country : "Spain"}
  },
  {
    path: "WishList",
    component: WishlistComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
