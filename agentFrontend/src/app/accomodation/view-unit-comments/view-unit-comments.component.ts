import { Komentar } from './../../model/rezervacija/komentar.model';
import { Component, OnInit, Input } from '@angular/core';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { analyzeAndValidateNgModules } from '@angular/compiler';

@Component({
  selector: 'app-view-unit-comments',
  templateUrl: './view-unit-comments.component.html',
  styleUrls: ['./view-unit-comments.component.css']
})
export class ViewUnitCommentsComponent implements OnInit {

  @Input()
  unit: SmestajnaJedinica;

  comments: Komentar[];

  constructor() { }

  ngOnInit() {
    this.comments = [{odobren: true, prilog: '', tekst: 'Jako uredna soba, na prelepoj lokaciji, ljubazni domacini.' +
    'Jedina zamerka je sto se posteljina nije presvlacila kako je bilo ugovoreno'},
    {odobren: true, prilog: '', tekst: 'Jako uredna soba, na prelepoj lokaciji, ljubazni domacini.' +
    'Jedina zamerka je sto se posteljina nije presvlacila kako je bilo ugovoreno'},
    {odobren: true, prilog: '', tekst: 'Jako uredna soba, na prelepoj lokaciji, ljubazni domacini.' +
    'Jedina zamerka je sto se posteljina nije presvlacila kako je bilo ugovoreno'},
    {odobren: true, prilog: '', tekst: 'Jako uredna soba, na prelepoj lokaciji, ljubazni domacini.' +
    'Jedina zamerka je sto se posteljina nije presvlacila kako je bilo ugovoreno'},
    {odobren: true, prilog: '', tekst: 'Jako uredna soba, na prelepoj lokaciji, ljubazni domacini.' +
    'Jedina zamerka je sto se posteljina nije presvlacila kako je bilo ugovoreno'},
  ];
  }

}
