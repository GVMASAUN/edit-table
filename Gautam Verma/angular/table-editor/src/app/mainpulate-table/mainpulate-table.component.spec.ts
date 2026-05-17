import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainpulateTableComponent } from './mainpulate-table.component';

describe('MainpulateTableComponent', () => {
  let component: MainpulateTableComponent;
  let fixture: ComponentFixture<MainpulateTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MainpulateTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MainpulateTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
