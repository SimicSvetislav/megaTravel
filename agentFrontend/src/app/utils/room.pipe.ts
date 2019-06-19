import { Pipe, PipeTransform } from '@angular/core';
import { RezervacijaKorisnika } from '../model/rezervacija/rezervacija-korisnika.model';

@Pipe({
    name: 'bookRoom'
  })
  export class RoomBookPipe implements PipeTransform {
    transform(value: RezervacijaKorisnika) {
      if (value) {
        const roomId = value.smestaj.id;
        // const objectName = value.smestaj.sObjekat.naziv;
        const objectName = 'XXXXX';

        return 'Objekat:' + objectName + '[Soba broj:' + roomId + ']';
      } else {
        return;
      }
    }
}
