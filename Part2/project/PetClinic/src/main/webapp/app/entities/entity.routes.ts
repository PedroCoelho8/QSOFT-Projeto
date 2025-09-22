import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'Authorities' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'owner',
    data: { pageTitle: 'Owners' },
    loadChildren: () => import('./owner/owner.routes'),
  },
  {
    path: 'pet',
    data: { pageTitle: 'Pets' },
    loadChildren: () => import('./pet/pet.routes'),
  },
  {
    path: 'pet-type',
    data: { pageTitle: 'PetTypes' },
    loadChildren: () => import('./pet-type/pet-type.routes'),
  },
  {
    path: 'specialty',
    data: { pageTitle: 'Specialties' },
    loadChildren: () => import('./specialty/specialty.routes'),
  },
  {
    path: 'vet',
    data: { pageTitle: 'Vets' },
    loadChildren: () => import('./vet/vet.routes'),
  },
  {
    path: 'visit',
    data: { pageTitle: 'Visits' },
    loadChildren: () => import('./visit/visit.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
