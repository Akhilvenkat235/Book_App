import { Injectable } from '@angular/core';
import { Book } from './books/book';
import { Favourite } from './favourite';
import { SearchserviceService } from './searchservice.service';
import { FavouriteService } from './favourite.service';

@Injectable({
  providedIn: 'root'
})
export class RecommendService {

  book: String;
  allBooks: Book[];
  allFavouriteBooks: Favourite[];
  allRecommendedBooks: Book[];
  userId:"madhu@gmail.com";
  constructor(private searchBookService: SearchserviceService, private favouriteService: FavouriteService) { }

  getRecomendedBooks():Book[] {
    this.searchBookService.getAllBooks().subscribe(

      data => {
        console.log("all books");
        this.allBooks = data;

        this.favouriteService.getAllFavourites(this.userId).subscribe(
          data => {
            console.log("favourite books");
            this.allFavouriteBooks = data;

            this.allRecommendedBooks = this.allBooks.filter(
              o =>
             {   this.allFavouriteBooks.
                  find(o2 => o.title_suggest === o2.title_name)
                  
                return this.allRecommendedBooks;  
            }
            )
          }
        )
      }

    );
    return this.allRecommendedBooks;  
  }
}
