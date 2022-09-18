import { Component, OnInit, Input, Inject } from '@angular/core';
import { Book } from '../books/book';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-bookdetail',
  templateUrl: './bookdetail.component.html',
  styleUrls: ['./bookdetail.component.css']
})
export class BookdetailComponent implements OnInit {

  @Input() book:Book
  constructor(public dialogRef: MatDialogRef< BookdetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {console.log("selected book : " +JSON.stringify(data) ); }
     
  ngOnInit() {}
  
  onCloseCancel(){
    this.dialogRef.close('Cancel');
  }

}
