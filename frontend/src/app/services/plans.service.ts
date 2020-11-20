import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {Exercise} from './exercises.service';

@Injectable({
  providedIn: 'root'
})
export class PlansService {

  constructor(private httpClient: HttpClient) { }

  public getPlan(): Observable<Plan[]>
  {
    return this.httpClient.get<Plan[]>(environment.apiUrl + '/api/plans/user');
  }
  public getPlanByDay(day: string): Observable<Plan[]>
  {
    return this.httpClient.get<Plan[]>(environment.apiUrl + '/api/plans/user/' + day);
  }
  public addPlan(exercise: string, day: string, weight: number, sets: number, reps: number, rpe: number): void{
    this.httpClient.post(environment.apiUrl + '/api/plans', {exercise: exercise.toString()
      , day: day.toString(), weight: weight.toString(), sets: sets.toString(), reps: reps.toString(), rpe: rpe.toString()} )
      .toPromise().then((data: Exercise) => { console.log(data); });
  }
  public getPlanVolumeToIntensity(id: Number,start: string, end: string): Observable<VolumeToIntensityChartData>{
    const params = new HttpParams()
      .set('id', id.toString())
      .set('start', start)
      .set('end', end);
    return this.httpClient.get<VolumeToIntensityChartData>(environment.apiUrl+
      '/api/plans/user/volume_to_intensity',{params})
  }
  public getPlanVolumes(start: string, end: string): Observable<VolumesByType>{
    const params = new HttpParams()
      .set('start', start)
      .set('end', end);
    return this.httpClient.get<VolumesByType>(environment.apiUrl+
      '/api/plans/user/lifts_volumes',{params})
  }
  public getExerciseStrengthTypes(start: string, end: string, id: Number): Observable<StrengthTypes>{
    const params = new HttpParams()
      .set('id', id.toString())
      .set('start', start)
      .set('end', end);
    return this.httpClient.get<StrengthTypes>(environment.apiUrl+
      '/api/plans/user/strength_types',{params})
  }
  public getPlanInformation(start: string, end: string, id: Number): Observable<PlanInformation>{
    const params = new HttpParams()
      .set('id', id.toString())
      .set('start', start)
      .set('end', end);
    return this.httpClient.get<PlanInformation>(environment.apiUrl+
      '/api/plans/user/prilepins_table_view',{params})
  }

}
export interface PlanInformation {
  minPercent: number;
  maxPercent: number;
  optPercent: number;
  repsFor55to65: number;
  repsFor70to80: number;
  repsFor80to90: number;
  repsForOver90: number;
  percentFor55to65: number;
  percentFor70to80: number;
  percentFor80to90: number;
  percentForOver90: number;
  warnings: any[];
}
export interface StrengthTypes {
  volumeForMaximalStrength: number;
  volumeForRest: number;
  volumeForOptimalRange: number;
  volumeForDynamic: number;
  volumeForRepetitionsMethod: number;
  volumeForSubmaximalMethod: number;
  totalVolume: number;
}

export interface Plan {
  id: number;
  user: number;
  exercise: string;
  day: Date;
  weight: number;
  sets: number;
  reps: number;
  rpe: number;
}
export interface VolumeToIntensityChartData {
  day: string[];
  exercise: string;
  volume: number[];
  intensity: number[];
  weight: number[];
  maxVolume: number;
  maxWeight: number;
}
export interface VolumesByType {
  mainLiftsVolume: number;
  accessoryLiftsVolume: number;
  supportLiftsVolume: number;
  mainLiftsVolumeInPercent: number;
  accessoryLiftsVolumeInPercent: number;
  supportLiftsVolumeInPercent: number;
}
