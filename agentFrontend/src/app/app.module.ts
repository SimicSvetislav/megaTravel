import { AccomodationService } from './service/accomodation.service';
import { BookingService } from './service/booking.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NgxBootstrapSliderModule } from 'ngx-bootstrap-slider';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarTopComponent } from './navbar-top/navbar-top.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { NewObjectComponent } from './accomodation/new-object/new-object.component';
import { NewUnitComponent } from './accomodation/new-unit/new-unit.component';
import { ViewObjectComponent } from './accomodation/view-object/view-object.component';
import { ViewUnitComponent } from './accomodation/view-unit/view-unit.component';
import { ViewAllObjectsComponent } from './accomodation/view-all-objects/view-all-objects.component';
import { ViewAllUnitsComponent } from './accomodation/view-all-units/view-all-units.component';
import { NewObjectBasicInfoComponent } from './accomodation/new-object-basic-info/new-object-basic-info.component';
import { ChooseFacilitiesComponent } from './accomodation/choose-facilities/choose-facilities.component';
import { RouterModule } from '@angular/router';
import { UserService } from './service/user.service';
import { NewUnitBasicInfoComponent } from './accomodation/new-unit-basic-info/new-unit-basic-info.component';
import { ChooseImagesComponent } from './accomodation/choose-images/choose-images.component';
import { DisplayImageComponent } from './accomodation/display-image/display-image.component';
import { ChoosePricelistComponent } from './accomodation/choose-pricelist/choose-pricelist.component';

const routes = [
  {
    path: 'newObject', component: NewObjectComponent
  },
  {
    path: 'object/:objectId/newUnit', component: NewUnitComponent
  },
  {
    path: '**', component: PageNotFoundComponent
  }];

@NgModule({
  declarations: [
    AppComponent,
    NavbarTopComponent,
    PageNotFoundComponent,
    LoginComponent,
    RegisterComponent,
    NewObjectComponent,
    NewUnitComponent,
    ViewObjectComponent,
    ViewUnitComponent,
    ViewAllObjectsComponent,
    ViewAllUnitsComponent,
    NewObjectBasicInfoComponent,
    ChooseFacilitiesComponent,
    NewUnitBasicInfoComponent,
    ChooseImagesComponent,
    DisplayImageComponent,
    ChoosePricelistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularFontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    NgxBootstrapSliderModule,
    HttpClientModule,
    RouterModule.forRoot(routes, {enableTracing: true})
  ],
  providers: [BookingService, AccomodationService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
