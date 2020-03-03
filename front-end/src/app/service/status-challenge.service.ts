import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { StatusChallenge } from '../models/status-challenge';

@Injectable({
  providedIn: 'root'
})
export class StatusChallengeService {
  constructor(private httpClient: HttpClient) { }
  private basePath = '/api/v1/challenge/status-challenge';
  statusChallengeSelected;

  getStatusChallenge(): Observable<Array<StatusChallenge>> {
    return this.httpClient.get<Array<StatusChallenge>>(environment.apiUrl + this.basePath);
  }

  getStatusChallengeSelected() {
    return this.statusChallengeSelected;
  }
}
