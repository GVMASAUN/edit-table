import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { DataService } from '../data.service';
import { TableModule } from 'primeng/table';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-mainpulate-table',
  standalone: true,
  imports: [
    TableModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule
  ],
  templateUrl: './mainpulate-table.component.html',
  styleUrl: './mainpulate-table.component.css'
})
export class MainpulateTableComponent {
  products: any;
  productForm: FormGroup;

  constructor(private dataService: DataService) {
    this.products = dataService.products;
    this.productForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      category: new FormControl('', [Validators.required]),
      price: new FormControl('', [Validators.required, Validators.pattern('^[0-9]*$')])
    });
  }

  get name() {
    return this.productForm.get('name');
  }

  get category() {
    return this.productForm.get('category');
  }

  get price() {
    return this.productForm.get('price');
  }
}
