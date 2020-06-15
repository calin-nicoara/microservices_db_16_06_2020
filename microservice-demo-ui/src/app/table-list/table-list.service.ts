import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class TableListService {

  constructor(private httpClient: HttpClient) {
  }

  getRows(url: string): Observable<any> {
    return this.httpClient.get(url)
  }
}
