import { Pipe, PipeTransform } from '@angular/core';
import { Otkazivanje } from '../model/smestaj/otkazivanje.model';

@Pipe({
    name: 'quitFilter'
  })
  export class QuitPipe implements PipeTransform {
    transform(value: Otkazivanje) {
      if (value) {
        if (value.dozvoljeno) {
            return value.brojDana + 'dana pre dolaska';
        } else {
            return 'Nije moguće';
        }
      } else {
        return;
      }
    }
}
