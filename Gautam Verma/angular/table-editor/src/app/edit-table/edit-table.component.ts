import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { TableModule } from 'primeng/table'
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-edit-table',
  standalone: true,
  imports: [
    TableModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    InputTextModule,
    ButtonModule
  ],
  templateUrl: './edit-table.component.html',
  styleUrl: './edit-table.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EditTableComponent implements OnInit {
  tableColumns: any;
  tableItems: any;
  mainForm!: FormGroup;
  formGroupArray!: FormArray;
  public isLoaded: boolean = false;

  constructor(private dataService: DataService, private formBuilder: FormBuilder) {
    this.tableColumns = this.dataService.getTableColumns();
    this.tableItems = this.dataService.getTableItems();

    this.mainForm = this.formBuilder.group({
      formGroupArray: this.formBuilder.array([])
    });
  }

  ngOnInit(): void {
    let isLoaded = false;
    this.formGroupArray = this.mainForm.get('formGroupArray') as FormArray;

    this.tableItems?.forEach((item: any) => {
      this.formGroupArray.push(
        this.formBuilder.group({
          field1: [item.propcol1, Validators.required],
          field2: [item.propcol2, Validators.required],
          field3: [item.propcol3, Validators.required],
          field4: [item.propcol4, Validators.required],
          field5: [item.propcol5, Validators.required],
          field6: [item.propcol6, Validators.required]
        })
      );
    });


  }


  submit(): void {
    console.log(this.mainForm.value);
    // console.log(this.formGroups.controls);
  }

  // get formGroups(): FormArray {
  //   return this.mainForm.get('formGroupArray') as FormArray;
  // }
}

