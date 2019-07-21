import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Track } from '../../track';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input()
  track: Track;

  @Input()
  wishData: boolean;

  @Output()
  trackEmitter = new EventEmitter();

  @Output()
  deletedTrack = new EventEmitter();

  @Output()
  updateComments = new EventEmitter();

  comment: string;

  constructor(private dialog: MatDialog) { }

  ngOnInit() {
  }

  addToWishList(track){
    console.log('Card Component: ', track);
    this.trackEmitter.emit(track);
  }

  deleteFromWishList(track){
    this.deletedTrack.emit(track);
  }

  addComments():void {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '500px',
      data: {comments: this.track.comments}
    });

    dialogRef.afterClosed().subscribe(result =>{
      console.log("Result: ", result);
      if(typeof result != 'undefined')
      {
        this.track.comments = result;
        this.updateComments.emit(this.track);
      }
    });
  }
}
