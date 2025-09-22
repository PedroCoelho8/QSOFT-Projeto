import { IOwner, NewOwner } from './owner.model';

export const sampleWithRequiredData: IOwner = {
  id: 12414,
  address: 'questionably accelerator',
  city: 'West Yessenia',
  firstName: 'Reilly',
  lastName: 'Stark',
  telephone: '564.304.1335 x7664',
};

export const sampleWithPartialData: IOwner = {
  id: 23648,
  address: 'dreamily drat too',
  city: 'East Bettie',
  firstName: 'Forrest',
  lastName: 'Kuhlman',
  telephone: '560.266.1376 x4652',
};

export const sampleWithFullData: IOwner = {
  id: 23742,
  address: 'hence',
  city: 'Pinkiechester',
  firstName: 'Otto',
  lastName: 'Harber',
  telephone: '566-590-0084 x912',
};

export const sampleWithNewData: NewOwner = {
  address: 'inasmuch until',
  city: 'Fort Jadeport',
  firstName: 'Keyshawn',
  lastName: 'Robel',
  telephone: '398-647-2017 x9294',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
