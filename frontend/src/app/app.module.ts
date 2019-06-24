import { EventBrokerService } from './services/event-broker/event-broker.service';
import { SocketService } from './services/chat/socket.service';
import { ChatService } from './services/chat/chat.service';


import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { HttpModule } from '@angular/http';
import { NgbModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { DatePipe } from '@angular/common';
import { ChatComponent } from './chat/chat.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
 
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { ReservationReportComponent } from './reservation-report/reservation-report.component';
import { PreviewComponent } from './preview/preview.component';
import { PreviewObjectComponent } from './preview-object/preview-object.component';

const appRoutes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile/:id', component: ProfileComponent},
  {path: 'reservation/report/:id', component: ReservationReportComponent},
  {path: 'chat/:id', component: ChatComponent},
  {path: 'preview', component: PreviewComponent},
  {path: 'preview/:id', component: PreviewObjectComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    ChatComponent,
    ReservationReportComponent,
    PreviewComponent,
    PreviewObjectComponent
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,{
        enableTracing: true
      }
    ),
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 4000,
      closeButton: true,
      positionClass: 'toast-top-right',
    }),
  ],
  providers: [DatePipe, ChatService, ToastrService, SocketService, EventBrokerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
