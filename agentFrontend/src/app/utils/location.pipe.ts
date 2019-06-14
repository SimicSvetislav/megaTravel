import { Lokacija } from './../model/smestaj/lokacija.model';
import { Pipe, PipeTransform } from '@angular/core';
import { stringify } from 'querystring';

@Pipe({
    name: 'locationFilter'
  })
  export class LocationPipe implements PipeTransform {
    transform(value: Lokacija) {
      if (value) {
        const naziv: string = value.naziv;
        if (value.geoDuzina) {
            const ret: string = naziv + '[' + value.geoDuzina.stepeni + value.geoDuzina.strana +
            ', ' + value.geoSirina.stepeni + value.geoSirina.strana + ']';
            return ret;
        }
        console.log(value);
        return naziv;
      } else {
        return;
      }
    }
}
