import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { ReactiveFormsModule }    from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material';
import {MatButtonModule} from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import{HttpClientModule} from '@angular/common/http';
import { SignInComponent } from './sign-in/sign-in.component';
import 'hammerjs';
import { LogoutComponent } from './logout/logout.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { BooksComponent } from './books/books.component';
//import { RecomendationsComponent } from './recomendations/recomendations.component';
import {MatSelectModule} from '@angular/material/select';
import {MatCardModule} from '@angular/material/card';
import { BookdetailComponent } from './bookdetail/bookdetail.component';
import {MatDialogModule, MAT_DIALOG_DEFAULT_OPTIONS} from '@angular/material/dialog';
import { FavouriteComponent } from './favourite/favourite.component';
import { RecommendComponent } from './recommend/recommend.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { Routes } from '@angular/router';



@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    SignInComponent,
    LogoutComponent,
    UpdateProfileComponent,
    BooksComponent,
    BookdetailComponent,
    FavouriteComponent,
    RecommendComponent,
    DashboardComponent,
    HomeComponent,
    HeaderComponent
    
   // RecomendationsComponent,
   
  ],

  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,    
 ReactiveFormsModule,
 MatFormFieldModule,
 MatInputModule,
 MatButtonModule,
 BrowserAnimationsModule,
 HttpClientModule,
 MatSelectModule,
 MatCardModule,
 MatDialogModule,
 MatToolbarModule
  ],

  providers: [],
  
  entryComponents: [BooksComponent,BookdetailComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
