import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'hash'
})
export class HashPipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): string {
    let reverse = '';
    for (let i = value.length - 1; i >= 0; i--) {
      reverse += value[i];
    }
    return reverse;
  }
}


