import { Component, OnInit } from '@angular/core';
import {Plan, PlansService} from '../../../services/plans.service';
import {DatePipe} from '@angular/common';
import {Exercise, ExercisesService} from '../../../services/exercises.service';
import {Observable} from 'rxjs';




@Component({
  selector: 'app-plans',
  templateUrl: './plans.component.html',
  styleUrls: ['./plans.component.scss']
})
export class PlansComponent implements OnInit {

  data: any;

  currentPlan: Promise<Plan>;

  plan: Plan[];
  displayedColumns: string[] = ['Exercise', 'Sets', 'Reps', 'Weight', 'RPE'];
  liftTypes: string[] = ['MAIN_LIFT', 'SUPPORT_LIFT', 'ACCESSORY'];
  exercises: Exercise[];
  myDate = new Date();
  liftType: string;
  exercise: string;

  constructor(private plansService: PlansService, private datePipe: DatePipe, private exercisesService: ExercisesService) {
  }

  currPlan: Plan;

  initPlan(): void
  {
    this.plansService.getPlanByDay(this.datePipe.transform(this.myDate, 'yyyy-MM-dd')).subscribe(value => {
      this.plan = value;
    });
  }

  initExercises(): void
  {
    this.exercisesService.getExercisesByType(this.liftType).subscribe(value => {this.exercises = value; });
  }

  ngOnInit(): void {
    this.initPlan();
    this.initChart();


  }

  nextDay(): void {
    this.myDate.setDate(this.myDate.getDate() + 1);
    this.myDate = new Date(this.myDate);
    this.initPlan();
  }

  previousDay(): void {
    this.myDate.setDate(this.myDate.getDate() - 1);
    this.myDate = new Date(this.myDate);
    this.initPlan();
  }

  savePlan(exercise: string, day: Date, weight: number, sets: number, reps: number, rpe: number): void {
    this.plansService.addPlan(exercise, this.datePipe.transform(this.myDate, 'yyyy-MM-dd'), weight, sets, reps, rpe);
    //console.log(this.currentPlan);
    this.initPlan();
    // DEBILIADA
    this.nextDay();
    this.previousDay();
  }

  initChart(): void{
    this.data = {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [
        {
          label: 'First Dataset',
          data: [65, 59, 80, 81, 56, 55, 40],
          fill: false,
          borderColor: '#4bc0c0'
        },
        {
          label: 'Second Dataset',
          data: [28, 48, 40, 19, 86, 27, 90],
          fill: false,
          borderColor: '#565656'
        }
      ]
    };
}
}
