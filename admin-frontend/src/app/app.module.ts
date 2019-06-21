import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AgentsComponent } from './agents/agents.component';
import { UsersComponent } from './users/users.component';
import { ExtrasComponent } from './extras/extras.component';
import { CategoriesComponent } from './categories/categories.component';
import { TypesComponent } from './types/types.component';
import { ProfileComponent } from './profile/profile.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { HttpClientModule } from '@angular/common/http';
import { AgentEditComponent } from './agents/agent-edit/agent-edit.component';
import { AgentAddComponent } from './agents/agent-add/agent-add.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { CommentsComponent } from './comments/comments.component';
import { AdminsComponent } from './admins/admins/admins.component';
import { EventBrokerService } from './services/event-broker.service';
import { AdminAddComponent } from './admins/admin-add/admin-add.component';

const appRoutes: Routes = [
  { path: 'profile', component: ProfileComponent},
  { path: 'agents', component: AgentsComponent },
  { path: 'agents/add', component: AgentAddComponent },
  { path: 'agents/:id', component: AgentEditComponent },
  { path: 'users', component: UsersComponent },
  { path: 'extras', component: ExtrasComponent },
  { path: 'categories', component: CategoriesComponent },
  { path: 'types', component: TypesComponent },
  { path: 'comments', component: CommentsComponent },
  { path: 'admins', component: AdminsComponent },
  { path: 'admins/add', component: AdminAddComponent },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  { path: '**', component: NotFoundComponent }
  
];

@NgModule({
  declarations: [
    AppComponent,
    AgentsComponent,
    UsersComponent,
    ExtrasComponent,
    CategoriesComponent,
    TypesComponent,
    ProfileComponent,
    NotFoundComponent,
    AgentEditComponent,
    AgentAddComponent,
    LoginComponent,
    RegisterComponent,
    CommentsComponent,
    AdminsComponent,
    AdminAddComponent
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true }
    ),
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 4000,
      closeButton: true,
      positionClass: 'toast-top-right',
    }),
  ],
  providers: [EventBrokerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
