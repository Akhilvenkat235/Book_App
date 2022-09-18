import { Component, OnInit } from '@angular/core';
import { RecommendService } from '../recommend.service';
import { Book } from '../books/book';

@Component({
  selector: 'app-recommend',
  templateUrl: './recommend.component.html',
  styleUrls: ['./recommend.component.css']
})
export class RecommendComponent implements OnInit {

  allRecommendBooks:Book[];
  constructor(private recommendService:RecommendService) { }

  ngOnInit() {

  this.allRecommendBooks=this.recommendService.getRecomendedBooks();

          return this.allRecommendBooks;
  }


}
