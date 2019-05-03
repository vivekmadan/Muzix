import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Track } from '../../track';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  comments: string;

  constructor(public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Track ) {
      this.comments = data.comments;
     }

  ngOnInit() {
  }
  
  onNoClick(){
    this.dialogRef.close();
  }

  addComments(){
    this.dialogRef.close(this.comments);
  }

}
