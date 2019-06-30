import { Rating } from './../rating';
import { AgentService } from './../services/users/agent.service';
import { CloudService } from './../services/cloud/cloud.service';
import { TokenStorageService } from './../services/auth/token-storage.service';
import { SmestajniObjekat, Cenovnik, DodatnaUsluga } from './../smestajniObjekat';
import { Route, Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ObjectService } from '../services/object/object.service';
import { SmestajnaJedinica } from '../smestajnaJedinica';
import { DomSanitizer } from '@angular/platform-browser';
import { ExtrasService } from '../services/search/extras.service';

@Component({
  selector: 'app-preview-object',
  templateUrl: './preview-object.component.html',
  styleUrls: ['./preview-object.component.scss']
})
export class PreviewObjectComponent implements OnInit {


  objectId;
  objekat: SmestajniObjekat = new SmestajniObjekat();
  showNavigationIndicators = true;
  podrazumevaniCenovnik: Cenovnik = new Cenovnik();
  logged: Boolean = false;
  id;
  existPricelist: Boolean = false;

  sjtemp: SmestajnaJedinica[] = [];
  sj: SmestajnaJedinica[] = [];
  objRating = 0;
  finalAddress: string = "";
  address: string = "";
  extras: DodatnaUsluga[] = [];
  comments: Rating[] = [];

  constructor(private router: Router, private agService: AgentService,
    private route: ActivatedRoute, private extrasService: ExtrasService,
    private soService: ObjectService,
    private tokenStorage: TokenStorageService,
    private cloudSe: CloudService, public sanitizer: DomSanitizer) { }

  ngOnInit() {

    this.objectId = this.route.snapshot.params['id'];

    if(this.tokenStorage.getUser() != null) {
      this.logged = true;
    }

    this.soService.getOneById(this.objectId).subscribe(data => {
      this.objekat = data;
      this.extras = data.dodatnaUsluga;

      this.address = this.objekat.lokacija.naziv;
      this.finalAddress = "https://maps.google.com/maps?q="+this.address+"&t=&z=13&ie=UTF8&iwloc=&output=embed";
      
      /*
      this.agService.getOneByObject(this.objectId).subscribe( data => {
        //this.address = data.adresa + ', ' + this.objekat.lokacija.naziv;
        this.address = this.objekat.lokacija.naziv;
        this.finalAddress = "https://maps.google.com/maps?q="+this.address+"&t=&z=13&ie=UTF8&iwloc=&output=embed";
      }, (error: Response) => {

      })*/

      this.podrazumevaniCenovnik = this.objekat.podrazumevaniCenovnik;
      
      if(this.podrazumevaniCenovnik == null) {
        this.existPricelist = false;
      } else {
        this.existPricelist = true;
      }
    })

    this.cloudSe.averageObject(this.objectId).subscribe(data => {
      this.objRating = data;
    })

    this.cloudSe.getRatingsByObjectApproved(this.objectId).subscribe(data => {
      this.comments = data;
    }, (e: Response) => {
      console.log(e);
    })


    this.soService.getUnitsOfObject(this.objectId).subscribe(data => {
        this.sjtemp = data;
        for(let s of this.sjtemp) {
          
          this.cloudSe.averageRoom(s.id).subscribe(data => {
            s.rejting = data;
            this.sj.push(s);
          })

         
        }
    })
   

  }

  rezervisi() {

  }

}
 