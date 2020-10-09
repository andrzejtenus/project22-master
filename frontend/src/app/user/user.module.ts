import { NgModule } from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {UserRoutingModule} from './user-routing.module';
import {HomePageComponent} from './components/home-page/home-page.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {ExercisesComponent} from './components/exercises/exercises.component';
import {TrainingComponent} from './components/training/training.component';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {TokenInterceptorService} from '../services/token-interceptor.service';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {FormsModule} from '@angular/forms';
import { PlansComponent } from './components/plans/plans.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {ChartModule} from 'primeng/chart';



@NgModule({
  declarations: [
    HomePageComponent,
    ExercisesComponent,
    TrainingComponent,
    PlansComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatInputModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    ChartModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    DatePipe
  ],
  bootstrap: [HomePageComponent]
})
export class UserModule { }
