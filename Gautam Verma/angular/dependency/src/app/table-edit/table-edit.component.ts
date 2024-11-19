import { ChangeDetectionStrategy, Component } from '@angular/core';
import { DataService } from '../data.service';
import { FormArray, FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-table-edit',
  templateUrl: './table-edit.component.html',
  styleUrls: ['./table-edit.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class TableEditComponent {
  tableColumns: any;
  tableItems: any;
  mainForm!: FormGroup;
  formGroupArray!: FormArray<FormGroup>;
  selected: FormGroup | null = null;
  dataRows: any;
  initFlag: boolean = false;
  responseData: any;

  constructor(private dataService: DataService, private formBuilder: FormBuilder) {
    this.tableColumns = this.dataService.getTableColumns();
    this.tableItems = this.dataService.getTableItems();
    this.responseData = dataService.responseData;

    this.mainForm = this.formBuilder.group({
      formGroupArray: this.formBuilder.array([])
    });
  }

  ngOnInit(): void {
    this.formGroupArray = this.mainForm.get('formGroupArray') as FormArray;
    let group: FormGroup;

    this.tableItems?.forEach((item: any) => {

      //alternate:
      // this.feedbackForm = new FormArray(this.data.map(x => new FormGroup({
      //   'question': new FormControl(x.description),
      //   'answer': new FormControl('')
      // })))
      //or
      // this.tableForm = this.fb.group({
      //   arrayForm: this.fb.array(this.results.map(r => this.fb.group(r)))
      // });

      group = this.formBuilder.group({
        id: [item.id, [Validators.required]],
        first_name: [item.first_name, [Validators.required, Validators.pattern("^[a-zA-Z ]+$")]],
        last_name: [item.last_name, [Validators.required, Validators.pattern("^[a-zA-Z ]+$")]],
        email: [item.email, [Validators.required, Validators.email]],
        gender: [item.gender, [Validators.required]],
        ip_address: [item.ip_address, [Validators.required]]
      });

      this.formGroupArray.push(group);
    });

    this.dataRows = this.formGroupArray.controls as FormGroup[];
    this.initFlag = true;
  }

  edit(rowData: any): void {
    if (!this.selected) {
      this.selected = rowData;
    }
    debugger;
    // this.patchErrors(rowData);
  }

  // private patchErrors(group: FormGroup): void {
  //   const item = this.responseData.find((i: { id: number, errors?: { [key: string]: string } }) => i.id === group.value.id);
  //   if (item.errors) {
  //     for (let field in item.errors) {
  //       if (item.errors.hasOwnProperty(field)) {
  //         const control = group.get(field);
  //         if (control) {
  //           control.setErrors({
  //             backendError: item.errors[field]
  //           });
  //           control.markAsDirty();
  //           control.markAsTouched();
  //           // control.disable();
  //           // control.updateValueAndValidity();
  //         }

  //       }
  //     }
  //   }
  // }

  private patchResponse(responseData: any): void {
    for (let iterator = 0; iterator < responseData.length; ++iterator) {
      let group: FormGroup = this.formGroupArray.at(iterator);
      const item = responseData[iterator];
      group.patchValue(item);

      if (item.errors) {
        for (let field in item.errors) {
          if (item.errors.hasOwnProperty(field)) {
            const control = group.get(field);
            if (control) {
              control.setErrors({
                backendError: item.errors[field]
              });
              control.markAsDirty();
              // control.markAsTouched();
              // control.disable();
              // control.updateValueAndValidity();
            }

          }
        }
      }
    }
  }

  trackById(index: number, formGroup: any): number {
    return index;
  }

  save(): void {
    this.selected = null;
  }

  submit(): void {
    console.log(this.mainForm.value);
    this.patchResponse(this.dataService.responseData);
  }
}
