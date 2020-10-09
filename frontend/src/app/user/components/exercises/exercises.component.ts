import { Component, OnInit } from '@angular/core';
import {Exercise, ExercisesService} from '../../../services/exercises.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-exercises',
  templateUrl: './exercises.component.html',
  styleUrls: ['./exercises.component.scss']
})
export class ExercisesComponent implements OnInit {

  // data
  mainLifts: Exercise[];
  supportLifts: Exercise[];
  accessories: Exercise[];
  // table columns
  displayedColumns: string[] = ['Lifts'];
  // added exercise
  addedExercise: Observable<Exercise>;



  constructor(private exercisesService: ExercisesService) { }


  ngOnInit(): void {
    this.initData();
  }



  initData(): void{
    this.exercisesService.getExercisesByType('MAIN_LIFT').subscribe(value => {
      this.mainLifts = value;
    });
    this.exercisesService.getExercisesByType('SUPPORT_LIFT').subscribe(value => {
      this.supportLifts = value;
    });
    this.exercisesService.getExercisesByType('ACCESSORY').subscribe(value => {
      this.accessories = value;
    });
  }


  addLift(name: string, type: string): void {
    this.exercisesService.addUserExercise(name.toLowerCase(), type);
    window.location.reload();
  }
}


