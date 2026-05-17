import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { TableModule } from 'primeng/table'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TableEditComponent } from './table-edit/table-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    TableEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
