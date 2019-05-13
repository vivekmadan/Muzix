import { Component, OnInit } from '@angular/core';
import { MuzixService } from '../../muzix.service';
import { Track } from '../../track';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  wishData = true;
  tracks: Array<Track>;

  constructor(private muzixService: MuzixService,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    const message = "Wishlist is empty";
    this.muzixService.getAllTracksForWishlist().subscribe(data =>{
      this.tracks = data;
      if(data.length === 0)
      {
        this.snackBar.open(message, "", {
          duration: 1000
        })
      }
    });
  }

  deleteTrackFromWishList(track){
    this.muzixService.deleteTrackFromWishList(track).subscribe(data =>{
      console.log("Deleted: ", data);
      const index = this.tracks.indexOf(track);
      this.tracks.splice(index, 1);
      this.snackBar.open("Successfully Deleted", "", {
        duration: 1000
      })
    });
  }

  updateComments(track){
    console.log("Track in Wishlist: ", track);
    this.muzixService.updateComments(track).subscribe(result =>{
      console.log("Result: ", result);
      this.snackBar.open("Successfully updated !!!", "", {
        duration: 1000
      })
    },
    error=>{
      console.log("error", error)
    });
  }


}
