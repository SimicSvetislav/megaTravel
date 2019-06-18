import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpModule } from '@angular/http';


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

const appRoutes: Routes = [
  { path: 'profile', component: ProfileComponent},
  { path: 'agents', component: AgentsComponent },
  { path: 'agents/add', component: AgentAddComponent },
  { path: 'agents/:id', component: AgentEditComponent },
  { path: 'users', component: UsersComponent },
  { path: 'extras', component: ExtrasComponent },
  { path: 'categories', component: CategoriesComponent },
  { path: 'types', component: TypesComponent },
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
    RegisterComponent
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true }
    ),
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
