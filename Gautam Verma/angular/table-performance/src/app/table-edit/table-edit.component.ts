import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-table-edit',
  templateUrl: './table-edit.component.html',
  styleUrls: ['./table-edit.component.css']
})
export class TableEditComponent implements OnInit {
  tableColumns: any;
  tableItems: any;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.tableColumns = this.dataService.getTableColumns();
    this.tableItems = this.dataService.getTableItems();
  }
}
