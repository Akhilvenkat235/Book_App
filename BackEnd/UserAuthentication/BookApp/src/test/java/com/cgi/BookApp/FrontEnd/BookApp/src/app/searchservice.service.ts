import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Book } from './books/book';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchserviceService {

  
  constructor(private httpClient: HttpClient) { }
  books: Array<Book>;
  getBooksBybookTitle(bookTitle: string):Observable<Book> {
return this.httpClient.get<Book>('http://openlibrary.org/search.json?title=' + bookTitle);
  }
  getBooksByAuthorName(authorName: string):Observable<Book> {
    return this.httpClient.get<Book>('http://openlibrary.org/search.json?author=' + authorName);

  }
  getAllBooks() {
    return this.httpClient.get<Book[]>('http://openlibrary.org/people/bookappp/lists.json');
      
   }
}
