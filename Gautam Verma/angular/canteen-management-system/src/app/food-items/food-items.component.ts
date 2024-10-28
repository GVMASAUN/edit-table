import { Component, OnInit } from '@angular/core';
import { FoodItemsService } from '../food-items.service';
import { FoodItem } from '../food-item';

@Component({
  selector: 'app-food-items',
  templateUrl: './food-items.component.html',
  styleUrls: ['./food-items.component.css']
})
export class FoodItemsComponent implements OnInit {
  foodItems: FoodItem[];
  constructor(private foodItemsService: FoodItemsService) { }

  ngOnInit(): void {
    this.foodItemsService.getFoodItems().subscribe(response => {
      this.foodItems = response?.foodItems;
    });
  }
}
