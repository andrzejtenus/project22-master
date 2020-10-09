import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomePageComponent} from './components/home-page/home-page.component';
import {LoginComponent} from '../components/login/login.component';
import {ExercisesComponent} from './components/exercises/exercises.component';
import {TrainingComponent} from './components/training/training.component';
import {PlansComponent} from './components/plans/plans.component';


const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
    children: [{path: 'exercises', component: ExercisesComponent},
      {path: 'training', component: TrainingComponent},
      {path: 'plans', component: PlansComponent}]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
