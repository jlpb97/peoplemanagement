import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Challenge } from '../models/challenge';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {
  constructor(private httpClient: HttpClient) { }
  private basePath = '/api/v1/challenge/';
  challengeSelected;
  addChallenge(challenge: Challenge) {
    return this.httpClient.post<Challenge>(environment.apiUrl + this.basePath, challenge);

  }
  editChallenge(challenge: Challenge): Observable<Challenge> {
    return this.httpClient.put<Challenge>(environment.apiUrl + this.basePath + challenge.id, challenge);
  }
  getChallenge(id: number) {
    return this.httpClient.get<Challenge>(environment.apiUrl + this.basePath + id);

  }

}
