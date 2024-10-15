import { Routes } from '@angular/router';
import { FirstComponentComponent } from './first-component/first-component.component';
import { SecondComponentComponent } from './second-component/second-component.component';

export const routes: Routes = [
    { path: "first-component", component: FirstComponentComponent },
    { path: "second-component", component: SecondComponentComponent },
];
