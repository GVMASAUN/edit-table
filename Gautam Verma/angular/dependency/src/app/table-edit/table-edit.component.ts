import { Component } from '@angular/core';
import { DataService } from '../data.service';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-table-edit',
  templateUrl: './table-edit.component.html',
  styleUrls: ['./table-edit.component.css'],
})
export class TableEditComponent {
  tableColumns: any;
  tableItems: any;
  mainForm!: FormGroup;
  formGroupArray!: FormArray;
  selected: FormGroup = this.formBuilder.group({});


  constructor(private dataService: DataService, private formBuilder: FormBuilder) {
    this.tableColumns = this.dataService.getTableColumns();
    this.tableItems = this.dataService.getTableItems();

    this.mainForm = this.formBuilder.group({
      formGroupArray: this.formBuilder.array([])
    });
  }

  ngOnInit(): void {
    this.formGroupArray = this.mainForm.get('formGroupArray') as FormArray;

    this.tableItems?.forEach((item: any) => {
      this.formGroupArray.push(
        this.formBuilder.group({
          propcol1: [item.propcol1, [Validators.required, Validators.minLength(3), Validators.pattern("^[a-zA-Z ]+$")]],
          propcol2: [item.propcol2, [Validators.required, Validators.pattern("^[0-9]{10}$")]],
          propcol3: [item.propcol3, [Validators.required, Validators.email]],
          propcol4: [item.propcol4, [Validators.required, Validators.email]],
          propcol5: [item.propcol5, [Validators.required, Validators.email]],
          propcol6: [item.propcol6, [Validators.required, Validators.email]]
        })
      );
    });
    this.tableItems?.forEach((item: any) => {
      this.formGroupArray.push(
        this.formBuilder.group({
          propcol1: [item.propcol1, [Validators.required, Validators.minLength(3), Validators.pattern("^[a-zA-Z ]+$")]],
          propcol2: [item.propcol2, [Validators.required, Validators.pattern("^[0-9]{10}$")]],
          propcol3: [item.propcol3, [Validators.required, Validators.email]],
          propcol4: [item.propcol4, [Validators.required, Validators.email]],
          propcol5: [item.propcol5, [Validators.required, Validators.email]],
          propcol6: [item.propcol6, [Validators.required, Validators.email]]
        })
      );
    }); this.tableItems?.forEach((item: any) => {
      this.formGroupArray.push(
        this.formBuilder.group({
          propcol1: [item.propcol1, [Validators.required, Validators.minLength(3), Validators.pattern("^[a-zA-Z ]+$")]],
          propcol2: [item.propcol2, [Validators.required, Validators.pattern("^[0-9]{10}$")]],
          propcol3: [item.propcol3, [Validators.required, Validators.email]],
          propcol4: [item.propcol4, [Validators.required, Validators.email]],
          propcol5: [item.propcol5, [Validators.required, Validators.email]],
          propcol6: [item.propcol6, [Validators.required, Validators.email]]
        })
      );
    });
  }

  edit(rowData: FormGroup): void {
    if (this.selected.valid) {
      this.selected = rowData;
    }
  }

  save(): void {
    this.selected = this.formBuilder.group({});
  }

  submit(): void {
    console.log(this.mainForm.value);
  }
}
