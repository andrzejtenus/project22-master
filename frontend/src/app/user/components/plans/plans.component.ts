import { Component, OnInit } from '@angular/core';
import {
  Plan,
  PlansService,
  StrengthTypes,
  VolumesByType,
  VolumeToIntensityChartData
} from '../../../services/plans.service';
import {DatePipe} from '@angular/common';
import {Exercise, ExercisesService} from '../../../services/exercises.service';
import {stringify} from 'querystring';
import {Observable} from "rxjs";
import * as moment from 'moment';
import { ChartType, ChartOptions } from 'chart.js';
import { SingleDataSet, Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip } from 'ng2-charts';
import * as Chart from 'chart.js'
import { timer } from 'rxjs';


@Component({
  selector: 'app-plans',
  templateUrl: './plans.component.html',
  styleUrls: ['./plans.component.scss']
})
export class PlansComponent implements OnInit {

  exerciseStrengthTypesChartData:StrengthTypes;
  volumeToIntensityChartData: VolumeToIntensityChartData;
  volumePieChartData: VolumesByType = {
    mainLiftsVolume: 0,
    accessoryLiftsVolume: 0,
    supportLiftsVolume: 0,
    mainLiftsVolumeInPercent: 0,
    accessoryLiftsVolumeInPercent: 0,
    supportLiftsVolumeInPercent: 0};
  data: any;
  piedata: any;
  plan: Plan[];
  displayedColumns: string[] = ['Exercise', 'Sets', 'Reps', 'Weight', 'RPE'];
  liftTypes: string[] = ['MAIN_LIFT', 'SUPPORT_LIFT', 'ACCESSORY'];
  chartLiftTypes: string[] = ['MAIN_LIFT', 'SUPPORT_LIFT', 'ACCESSORY'];
  exercises: Exercise[];
  chartExercises: Exercise[];
  myDate = new Date();
  endDate:string;
  startDate:string;


  chartLiftType: string;
  liftType: string;
  chartExercise: Exercise;
  exercise: string;

  constructor(private plansService: PlansService, private datePipe: DatePipe
              , private exercisesService: ExercisesService) {
    monkeyPatchChartJsTooltip();
    monkeyPatchChartJsLegend();
  }
  initPlan(): void
  {
    this.plansService.getPlanByDay(this.datePipe.transform(this.myDate, 'yyyy-MM-dd')).subscribe(value => {
      this.plan = value;
    });
  }
  initBasicChartData():void{

    this.chartLiftType = 'MAIN_LIFT';
    this.exercisesService.getExercisesByType('MAIN_LIFT').subscribe(value => {this.chartExercises = value;
      this.chartExercise = this.chartExercises[0];
      this.initChartsData(this.startDate,this.endDate);
      this.initVolumePieChartData(this.startDate,this.endDate);
      this.initStrengthTypesChartData(this.startDate, this.endDate, this.chartExercise.id);
    });
  }

  initChartsData(start: string, end: string):void
  {
    this.plansService.getPlanVolumeToIntensity(this.chartExercise.id, start, end).subscribe(value => {
      this.volumeToIntensityChartData = value;
      this.initChart();
    });
  }


  initExercises(): void
  {
    this.exercisesService.getExercisesByType(this.liftType).subscribe(value => {this.exercises = value; });
  }

  initChartExercises(): void {
    this.exercisesService.getExercisesByType(this.chartLiftType).subscribe(value =>
    {this.chartExercises = value; });
  }

  ngOnInit(): void {
    var newDate= new Date(this.myDate);
    this.startDate=this.datePipe.transform(new Date(newDate.setDate(this.myDate.getDate() - 2100)), 'yyyy-MM-dd');
    this.endDate=this.datePipe.transform(new Date(newDate.setDate(this.myDate.getDate() + 4200)), 'yyyy-MM-dd');
    this.initPlan();
    this.initBasicChartData();
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
    this.plansService.addPlan(exercise, this.datePipe.transform(this.myDate, 'yyyy-MM-dd')
      , weight, sets, reps, rpe);


    setTimeout(()=> {
      this.initChartsData(this.startDate,this.endDate);
      this.initVolumePieChartData(this.startDate,this.endDate);
      this.initStrengthTypesChartData(this.startDate, this.endDate, this.chartExercise.id);
      this.initPlan();
    }, 100);
  }
  initStrengthTypesChartData(startDate: string, endDate:string, id:number): void{
    this.plansService.getExerciseStrengthTypes(startDate, endDate, id)
      .subscribe(value => {
        this.exerciseStrengthTypesChartData=value;
        console.log(this.exerciseStrengthTypesChartData);
        this.initStrengthTypeChartData();
      });
  }

  initChart(): void{
    this.data = {

      labels: this.volumeToIntensityChartData.day,
      datasets: [
        {
          label: 'volume (% of '+this.volumeToIntensityChartData.maxVolume+ ')',
          data: this.volumeToIntensityChartData.volume,
          fill: false,
          borderColor: '#4bc0c0'
        },
        {
          label: 'intensity in %',
          data: this.volumeToIntensityChartData.intensity,
          fill: false,
          borderColor: '#565656'
        },
        {
          label: 'weight (% of'+this.volumeToIntensityChartData.maxWeight+ ')',
          data: this.volumeToIntensityChartData.weight,
          fill: false,
          borderColor: '#003333'
        }
      ]
    };
}

  applyChartData(start: string, end: string) {
    if(start)
    this.startDate=moment(start, 'MM/DD/YYYY').format('YYYY-MM-DD');
    if(end)
    this.endDate=moment(end, 'MM/DD/YYYY').format('YYYY-MM-DD');
    this.initChartsData(this.startDate,this.endDate);
     this.initVolumePieChartData(this.startDate,this.endDate);
     this.initStrengthTypesChartData(this.startDate, this.endDate, this.chartExercise.id);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////

  public initVolumePieChartData(start: string, end: string): void{
    this.plansService.getPlanVolumes(start, end).subscribe(value => {
      this.volumePieChartData = value;
      this.initPieChartData();
    });
  }
  canvas: any;
  ctx: any;

  public initPieChartData() {
    this.canvas = document.getElementById('myLiftTypeChart');
    this.ctx = this.canvas.getContext('2d');
    let myChart = new Chart(this.ctx, {
      type: 'pie',
      data: {
        labels: ['Main Lifts ('+Math.round(this.volumePieChartData.mainLiftsVolumeInPercent*100) +'%)'
          , 'Support Lifts ('+Math.round(this.volumePieChartData.supportLiftsVolumeInPercent*100)+ '%)'
          , 'Accesories ('+Math.round(this.volumePieChartData.accessoryLiftsVolumeInPercent*100)+'%)'],
        datasets: [{
          label: '# of Votes',
          data: [this.volumePieChartData.mainLiftsVolume,this.volumePieChartData.supportLiftsVolume
            ,this.volumePieChartData.accessoryLiftsVolume],
          backgroundColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {}
    });
  }
  public initStrengthTypeChartData() {
    this.canvas = document.getElementById('myStrengthTypesChart');
    this.ctx = this.canvas.getContext('2d');
    let myChart = new Chart(this.ctx, {
      type: 'pie',
      data: {
        labels: ['Maximal Strength ('+Math.round(
          this.exerciseStrengthTypesChartData.volumeForMaximalStrength
          /this.exerciseStrengthTypesChartData.totalVolume*100) +'%)'
          , 'Power ('+Math.round(this.exerciseStrengthTypesChartData.volumeForDynamic
            /this.exerciseStrengthTypesChartData.totalVolume*100)+ '%)'
          , 'Repetitions Method ('+Math.round(this.exerciseStrengthTypesChartData.volumeForRepetitionsMethod
            /this.exerciseStrengthTypesChartData.totalVolume*100)+'%)',
          'Submaksimal Method ('+Math.round(this.exerciseStrengthTypesChartData.volumeForSubmaximalMethod
            /this.exerciseStrengthTypesChartData.totalVolume*100)+'%)'],

        datasets: [{
          label: '# of Votes',
          data: [this.exerciseStrengthTypesChartData.volumeForMaximalStrength,
            this.exerciseStrengthTypesChartData.volumeForDynamic,
            this.exerciseStrengthTypesChartData.volumeForRepetitionsMethod,
            this.exerciseStrengthTypesChartData.volumeForSubmaximalMethod],

          backgroundColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(51, 255, 65, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {}
    });
  }

}
