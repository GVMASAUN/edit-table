import { Directive, Input, OnChanges, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appMyNgFor]'
})
export class MyNgForDirective implements OnChanges {
  @Input() appMyNgForOf: Array<any>;

  constructor(private templateRef: TemplateRef<any>, private viewContainer: ViewContainerRef) { }

  ngOnChanges(): void {
    this.viewContainer.clear();

    for (const input of this.appMyNgForOf) {
      this.viewContainer.createEmbeddedView(this.templateRef, {
        $implicit: input,
        index: this.appMyNgForOf.indexOf(input)
      });
    }
  }
}
