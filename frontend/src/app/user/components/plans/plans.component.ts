import { Component, OnInit } from '@angular/core';
import {Plan, PlansService, VolumeToIntensityChartData} from '../../../services/plans.service';
import {DatePipe} from '@angular/common';
import {Exercise, ExercisesService} from '../../../services/exercises.service';
import {stringify} from "querystring";
import {Observable} from "rxjs";




@Component({
  selector: 'app-plans',
  templateUrl: './plans.component.html',
  styleUrls: ['./plans.component.scss']
})
export class PlansComponent implements OnInit {

  volumeToIntensityChartData: VolumeToIntensityChartData;
  data: any;
  plan: Plan[];
  displayedColumns: string[] = ['Exercise', 'Sets', 'Reps', 'Weight', 'RPE'];
  liftTypes: string[] = ['MAIN_LIFT', 'SUPPORT_LIFT', 'ACCESSORY'];
  exercises: Exercise[];
  myDate = new Date();
  liftType: string;
  exercise: string;

  constructor(private plansService: PlansService, private datePipe: DatePipe, private exercisesService: ExercisesService) {
  }
  initPlan(): void
  {
    this.plansService.getPlanByDay(this.datePipe.transform(this.myDate, 'yyyy-MM-dd')).subscribe(value => {
      this.plan = value;
    });
  }
  initChartsData():void
  {
    this.plansService.getPlanVolumeToIntensity().subscribe(value => {
      this.volumeToIntensityChartData = value;
      console.log(value);
      this.initChart();
    });
  }

  initExercises(): void
  {
    this.exercisesService.getExercisesByType(this.liftType).subscribe(value => {this.exercises = value; });
  }


  ngOnInit(): void {
    this.initPlan();
    this.initChartsData();
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
    //this.initPlan();
    // DEBILIADA
    this.nextDay();
    this.previousDay();
  }

  initChart(): void{
    this.data = {

      labels: this.volumeToIntensityChartData.day,
      datasets: [
        {
          label: 'volume',
          data: this.volumeToIntensityChartData.volume,
          fill: false,
          borderColor: '#4bc0c0'
        },
        {
          label: 'intensity',
          data: this.volumeToIntensityChartData.intensity,
          fill: false,
          borderColor: '#565656'
        }
      ]
    };
}
}
