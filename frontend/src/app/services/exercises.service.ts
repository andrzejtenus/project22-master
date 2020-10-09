import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {share} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ExercisesService {


  constructor(private httpClient: HttpClient) {}

  public getExercisesByType(type: string): Observable<Exercise[]>
  {
    return this.httpClient.get<Exercise[]>(environment.apiUrl + '/api/exercises/user/type/' + type);
  }

  public addUserExercise(name: string, type: string): void
  {
    this.httpClient.post(environment.apiUrl + '/api/exercises', {name: name.toString(), type: type.toString()} )
      .toPromise().then((data: Exercise) => { console.log(data); });
  }

}
export interface Exercise {
  id: number;
  name: string;
  type: string;
  user: number;
}
