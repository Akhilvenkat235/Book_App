import { Component, OnInit } from '@angular/core';
import { Favourite } from '../favourite';
import { BookdetailComponent } from '../bookdetail/bookdetail.component';
import { FavouriteService } from '../favourite.service';
import { LoginService } from '../login.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {

  selectedBook: Favourite;
  favourite: Array<Favourite>;
  fav: Favourite;

favourites:Favourite[];
baseURL = "http://covers.openlibrary.org/b/isbn/"
  constructor(private favouriteService:FavouriteService,private authService: LoginService,private dailogue:MatDialog) {
   
  }
 
  ngOnInit() {
    let userId=this.authService.getUserEmail();
    this.favouriteService.getAllFavourites(userId).subscribe(
      data=>{
          console.log(JSON.stringify(data))
          this.favourites=data;
          // JSON.stringify(this.favourites)
      }
    )
  }
  
 
 
  
  clickUnfavourite(isbn:string) {
   // this.favourites=new Favourite;
  let userId=this.authService.getUserEmail();
    //this.fav.isbn=isbn;
    console.log("favourite deleted");
    this.favouriteService.deleteFavourite(userId,isbn).subscribe(
      response => {
                 alert(JSON.stringify(response));
                
      },
      error=>{
        
        if(error.status==200){
        alert("Favourite deleted successfully");
        this.ngOnInit();
        }else{
        alert("favourite does not exists");
      }
    }
    )
  
  }
   


  
  onSelect(favourite: Favourite): void {
    this.selectedBook = favourite
   let dailogRef = this.dailogue.open(BookdetailComponent,{
    width:'600px',
    height:'65%',
    
    data:{selectedBook:favourite}
     
    }); 

  }

}
