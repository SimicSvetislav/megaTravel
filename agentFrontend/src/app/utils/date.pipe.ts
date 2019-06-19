import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'dateFilter'
  })
  export class DatePipe implements PipeTransform {
    transform(value: string) {
      // const num: number =  value.getDate();

      if (value) {
        const end: number =  value.indexOf('T');
        const str = value.slice(0, end);

        console.log(value);
        return str;
      } else {
        return;
      }
    }
}

