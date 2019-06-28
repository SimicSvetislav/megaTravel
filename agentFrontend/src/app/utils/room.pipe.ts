import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { Pipe, PipeTransform } from '@angular/core';
import { RezervacijaKorisnika } from '../model/rezervacija/rezervacija-korisnika.model';

@Pipe({
    name: 'bookRoom'
  })
  export class RoomBookPipe implements PipeTransform {
    transform(value: RezervacijaKorisnika) {
      if (value) {
        const roomDTO: SmestajnaJedinica = value.smestajnaJedinicaDTO;
        return 'Objekat: ' + roomDTO.sObjekatDTO.naziv + '[ Soba oznaka:' + roomDTO.oznaka + ']';
      } else {
        return;
      }
    }
}
