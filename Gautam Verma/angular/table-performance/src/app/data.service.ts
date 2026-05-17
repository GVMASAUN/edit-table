import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }

  tableColumns = [
    { field: 'propName', header: 'Name' },
    { field: 'propValue', header: 'Value' },
    { field: 'propValueType', header: 'Type' },
  ];

  tableItems = [
    { propName: 'prop 1', propValue: 'value 1', propValueType: 'Email' },
    { propName: 'prop 2', propValue: 'true', propValueType: 'Text' },
    { propName: 'prop 3', propValue: 'value 3', propValueType: 'Number' },
    { propName: 'prop 4', propValue: 'true', propValueType: 'Date' },
    { propName: 'prop 5', propValue: 'value 5', propValueType: 'Month' },
    { propName: 'prop 6', propValue: 'true', propValueType: 'Email' },
    { propName: 'prop 7', propValue: 'value 7', propValueType: 'Text' },
    { propName: 'prop 8', propValue: 'true', propValueType: 'Time' },
    { propName: 'prop 9', propValue: 'value 9', propValueType: 'Text' },
    { propName: 'prop 10', propValue: 'true', propValueType: 'Email' },
  ];

  getTableColumns(): any {
    return this.tableColumns;
  }

  getTableItems(): any {
    return this.tableItems;
  }

}
