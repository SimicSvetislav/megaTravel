import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'dateFilter'
  })
  export class DatePipe implements PipeTransform {
    transform(value: Date) {
      const num: number =  value.getDate();
      console.log(num);
      if (value) {
        console.log(value);
        return value.toString();
      } else {
        return;
      }
    }
}

