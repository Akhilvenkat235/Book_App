import { Component, OnInit } from '@angular/core';
import { SearchserviceService } from '../searchservice.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Book } from './book';
import { BookdetailComponent } from '../bookdetail/bookdetail.component';
import {MatDialog} from '@angular/material/dialog';
import { FavouriteService } from '../favourite.service';
import { LoginService } from '../login.service';
import { Favourite } from '../favourite';
import { RouterServiceService } from '../router-service.service';
@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  searchForm=new FormGroup({
    searchText:new FormControl(''),
    selectedValue:new FormControl('')
  });
  selectedBook: Book;
  baseURL="http://covers.openlibrary.org/b/isbn/"
  books: Array<any>
  bookTitle: string;
  authorName: string;
  isbn: string;
  iccn:String;
  searchField:string;
  selectedOption:string;
  favourite:Favourite;
  constructor(private searchService: SearchserviceService,public dialog: MatDialog
    ,private favouriteService:FavouriteService,private authourization:LoginService,
    private route:RouterServiceService) {
    
   }
   temp:Array<any>;
  ngOnInit() {
  this.getAllBooks();
  
  }
  getAllBooks(){
    this.searchService.getBooksBybookTitle('twilight').subscribe(data =>{
      this.books = data['docs'];
      console.log(this.books);
    })
  }
  onSearch()
  {
    console.log(this.searchForm.value);
   // console.log(this.searchForm.searchText.value);
    console.log(' search');
  
  this.temp == this.searchForm.value;
console.log(this.temp)
/*if(this.searchForm.value.searchText == null){
  console.log('enter the text ')
}*/
   if (this.searchForm.value.selectedValue== 'Title') {
   this.searchService.getBooksBybookTitle(this.searchForm.value.searchText).subscribe(
     data => {
      //console.log("data: "+JSON.stringify(data));
this.books = data['docs'];
console.log("searched books  by title : " + JSON.stringify(this.books));
     }
     );
 } else if (this.searchForm.value.selectedValue== 'Author') {
   this.searchService.getBooksByAuthorName(this.searchForm.value.searchText).subscribe(
     data=>{
    // console.log("data: "+data);
     this.books = data['docs'];
     console.log("searched books  by author : " + JSON.stringify(this.books));

   });
 }
  }

  onSelect(book: Book): void {
    this.selectedBook = book
   let dailogRef =this.dialog.open(BookdetailComponent,{
    width:'600px',
    height:'65%',
    
    data:{selectedBook:book}
     
    }); 

}

clickFavourite(book :Book, isbn:string) {
  if(this.authourization.getBearerToken()!=null){

  this.favourite=new Favourite;
  this.favourite.favouriteId=Math.round(Math.random()*10000);
  this.favourite.userEmail=this.authourization.getUserEmail();
  this.favourite.isbn=isbn[0];
  this.favourite.author_name=book.author_name[0];
  this.favourite.title_name=book.title_suggest;
    console.log("favourite added");
    this.favouriteService.addFavourite(this.favourite).subscribe(
          response => {
                     alert(JSON.stringify(response));
                     alert("Added favourite");
          },
          error=>{
console.log(error.error)
            if(error.status==201)
            alert("Added favourite");
            else
            alert("favourite already exists");
          }


    )

}else{
  alert('Please login')
 this.route.routeToLogin();
}
}







}