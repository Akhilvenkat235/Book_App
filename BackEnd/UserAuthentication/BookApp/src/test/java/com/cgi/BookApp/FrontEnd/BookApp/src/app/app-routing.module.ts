import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BooksComponent } from './books/books.component';
import { RecommendComponent } from './recommend/recommend.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { LogoutComponent } from './logout/logout.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { Gaurd } from './guard';


export const routes: Routes = [
  {path: '',
   redirectTo: '/home', 
   pathMatch: 'full' } ,
   {
  path: 'search',
  component: BooksComponent
   },
    {
       path: 'login', 
       component: SignInComponent
    },
    {
      path: 'dashboard', 
      component: DashboardComponent,
    },
    {
      path: 'register',
      component: RegisterComponent
    },
    {
      path:'home',
      component:HomeComponent
    },
    {
      path:'update',
      component:UpdateProfileComponent
    },
    {
      path:'logout',
      component:LogoutComponent
    },{
      path:'favourite',
      component:FavouriteComponent
       },
    {
      path:'recommend',
      component:RecommendComponent
    }
  ];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

