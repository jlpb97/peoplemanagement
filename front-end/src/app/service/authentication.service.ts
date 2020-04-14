import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Session } from '../models/session';
import { LoginObject } from '../models/login';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) {}

  private basePath = '/api/v1/auth/';
  // tslint:disable-next-line: use-life-cycle-interface
  ngOnInit() {}

  login(loginObj: LoginObject): Observable<Session> {
    return this.http.post<Session>(environment.apiUrl + this.basePath + 'login', loginObj);
  }
}
