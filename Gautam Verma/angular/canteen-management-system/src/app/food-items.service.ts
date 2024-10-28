import { Injectable } from '@angular/core';
import { FoodItem } from './food-item';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FoodItemsService {
  constructor(private http: HttpClient) { }

  getFoodItems(): Observable<any> {
    return this.http.get<any>("http://localhost:8080/item");
  }
}
