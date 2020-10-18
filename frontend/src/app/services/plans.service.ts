import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {Exercise} from './exercises.service';
import {share} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PlansService {

  currPlan: Plan;

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

  public async addPlan2(exercise: string, day: string, weight: number, sets: number, reps: number, rpe: number): Promise<Plan> {
      const resp = await this.httpClient.post<Plan>(environment.apiUrl + '/api/plans', {
      exercise: exercise.toString()
      ,
      day: day.toString(),
      weight: weight.toString(),
      sets: sets.toString(),
      reps: reps.toString(),
      rpe: rpe.toString()
    });
      console.log(resp);
      return null;
  }

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
