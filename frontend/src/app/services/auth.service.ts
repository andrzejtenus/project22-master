import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {share} from 'rxjs/operators';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  signIn(user: {username: string, password: string}): Observable<any>{
    const request = this.Http.post<Token>(environment.apiUrl + '/api/auth/signin', user).pipe(share());
    request.subscribe(res => {
      localStorage.setItem('currentUser', JSON.stringify(res.tokenType + ' ' + res.accessToken));
    }, err => {
        console.log('login error');
      }
    );
    return request;
  }
  signOut(): void {
    localStorage.removeItem('currentUser');
  }
  constructor(private Http: HttpClient) { }
}

export interface Token {
  accessToken: string;
  tokenType: string;
}

