import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './pages/login/login.component';


const routes: Routes = [{
  path: '',
  pathMatch: 'full',
  redirectTo: '/index'
}, {
  path: 'index',
  loadChildren: () => import('./app.module').then(m => m.AppModule)
}, {
  path: 'login',
  component: LoginComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
