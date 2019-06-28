import { RbmPanelComponent } from './rbm/rbm-panel/rbm-panel.component';
import { HomeComponent } from './home/home.component';
import { ChatService } from './service/chat.service';
import { ProfileComponent } from './user/profile/profile.component';
import { LocationPipe } from './utils/location.pipe';
import { DatePipe } from './utils/date.pipe';
import { AccomodationService } from './service/accomodation.service';
import { BookingService } from './service/booking.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component, APP_INITIALIZER } from '@angular/core';
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
import { ViewObjectBasicInfoComponent } from './accomodation/view-object-basic-info/view-object-basic-info.component';
import { ViewObjectFacilitiesComponent } from './accomodation/view-object-facilities/view-object-facilities.component';
import { ViewObjectImagesComponent } from './accomodation/view-object-images/view-object-images.component';
import { ViewUnitBasicInfoComponent } from './accomodation/view-unit-basic-info/view-unit-basic-info.component';
import { ViewUnitPricesComponent } from './accomodation/view-unit-prices/view-unit-prices.component';
import { ViewUnitCommentsComponent } from './accomodation/view-unit-comments/view-unit-comments.component';
import { ViewUnitBookingComponent } from './accomodation/view-unit-booking/view-unit-booking.component';
import { ViewAllMessagesComponent } from './booking/view-all-messages/view-all-messages.component';
import { ViewMessageComponent } from './booking/view-message/view-message.component';
import { AnswerMessageComponent } from './booking/answer-message/answer-message.component';
import { ViewAllMessagesTableComponent } from './booking/view-all-messages-table/view-all-messages-table.component';
import { ViewAllBookingsComponent } from './booking/view-all-bookings/view-all-bookings.component';
import { ViewBookingComponent } from './booking/view-booking/view-booking.component';
import { RoomBookPipe } from './utils/room.pipe';
import { UserBookPipe } from './utils/user.pipe';
import { LogoutComponent } from './user/logout/logout.component';
import { AppConfigService } from './service/app-config.service';
import { CommentMessageService } from './service/comment-message.service';
import { TokenStorageService } from './service/auth/toke-storage.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { ChatComponent } from './booking/chat/chat.component';
import { SocketService } from './service/socket.service';
const routes = [
  {
    path: '', redirectTo: '/home', pathMatch: 'full'
  },
  {
    path: 'home', component: HomeComponent,
  },
  {
    path: 'objects', component: ViewAllObjectsComponent,
  },
  {
    path: 'objects', component: ViewAllObjectsComponent,
  },
  {
    path: 'newObject', component: NewObjectComponent
  },
  {
    path: 'object/:objectId/newUnit', component: NewUnitComponent
  },
  {
    path: 'object/:objectId', component: ViewObjectComponent
  },
  {
    path: 'object/:objectId/:units', component: ViewObjectComponent
  },
  {
    path: 'object/:objectId/unit/:unitId', component: ViewUnitComponent
  },
  {
    path: 'messages', component: ViewAllMessagesComponent
  },
  {
    path: 'bookings', component: ViewAllBookingsComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'register', component: RegisterComponent
  },
  {
    path: 'logout', component: LogoutComponent
  },
  {
    path: 'profile', component: ProfileComponent
  },
  {
    path: 'chat/:resId', component: ChatComponent
  },
  {
    path: 'rbm', component: RbmPanelComponent
  },
  {
    path: '**', component: PageNotFoundComponent
  }];

  export function initializeApp(appConfig: AppConfigService) {
    return () => appConfig.load();
  }
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarTopComponent,
    PageNotFoundComponent,
    LoginComponent,
    LogoutComponent,
    ProfileComponent,
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
    ChoosePricelistComponent,
    DatePipe,
    LocationPipe,
    RoomBookPipe,
    UserBookPipe,
    ViewObjectBasicInfoComponent,
    ViewObjectFacilitiesComponent,
    ViewObjectImagesComponent,
    ViewUnitBasicInfoComponent,
    ViewUnitPricesComponent,
    ViewUnitCommentsComponent,
    ViewUnitBookingComponent,
    ViewAllMessagesComponent,
    ViewMessageComponent,
    AnswerMessageComponent,
    ViewAllMessagesTableComponent,
    ViewAllBookingsComponent,
    ViewBookingComponent,
    ChatComponent,
    RbmPanelComponent
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
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 4000,
      closeButton: true,
      positionClass: 'toast-top-right',
    }),
    RouterModule.forRoot(routes, {enableTracing: true}) // <-- debugging purposes only
  ],
  providers: [BookingService,
              AccomodationService,
              CommentMessageService,
              UserService,
              TokenStorageService,
              ToastrService,
              SocketService,
              ChatService,
              { provide: APP_INITIALIZER, useFactory: initializeApp, deps: [AppConfigService], multi: true }
            ],
  bootstrap: [AppComponent]
})
export class AppModule { }
