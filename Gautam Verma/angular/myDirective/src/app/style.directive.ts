import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appStyle]'
})
export class StyleDirective {

  @Input() appStyle: string;

  constructor(private elementRef: ElementRef) { }

  @HostListener('mouseenter') onMouseEnter() {
    this.highlight(this.appStyle || 'yellow');
  }

  @HostListener('mouseleave') onMouseLeave() {
    this.highlight('green');
  }

  private highlight(color: string) {
    this.elementRef.nativeElement.style.backgroundColor = color;
  }
}
