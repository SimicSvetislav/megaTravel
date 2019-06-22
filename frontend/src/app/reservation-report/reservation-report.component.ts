import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../services/reservations/reservation.service';

@Component({
  selector: 'app-reservation-report',
  templateUrl: './reservation-report.component.html',
  styleUrls: ['./reservation-report.component.scss']
})
export class ReservationReportComponent implements OnInit {

  htmlString = '';

  constructor(private route: ActivatedRoute, private service: ReservationService,
              private toastr: ToastrService) { }

  ngOnInit() {

    const id = this.route.snapshot.params['id'];

    this.service.getReport(id).subscribe( data => {
      this.htmlString = data;
    }, error => {
      console.log(error);
      this.toastr.error("Error occured while generating HTML report");
      this.htmlString = '<p><strong>Generation failed</strong></p>';
    });

    this.htmlString = '<p><strong>Waiting for html to generate...</strong></p>';
  }

}
