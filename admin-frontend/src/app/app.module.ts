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

const appRoutes: Routes = [
  { path: 'profile', component: ProfileComponent},
  { path: 'agents', component: AgentsComponent },
  { path: 'agents/:id', component: AgentEditComponent },
  { path: 'users', component: UsersComponent },
  { path: 'extras', component: ExtrasComponent },
  { path: 'categories', component: CategoriesComponent },
  { path: 'types', component: TypesComponent },
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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true }
    ),
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
