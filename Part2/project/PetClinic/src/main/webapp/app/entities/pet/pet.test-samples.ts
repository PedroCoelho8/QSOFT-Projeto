import dayjs from 'dayjs/esm';

import { IPet, NewPet } from './pet.model';

export const sampleWithRequiredData: IPet = {
  id: 31638,
  name: 'wherever repeatedly',
  birthDate: dayjs('2024-11-18T19:23'),
};

export const sampleWithPartialData: IPet = {
  id: 17731,
  name: 'midst barracks',
  birthDate: dayjs('2024-11-18T17:17'),
};

export const sampleWithFullData: IPet = {
  id: 583,
  name: 'guacamole ha',
  birthDate: dayjs('2024-11-18T03:26'),
};

export const sampleWithNewData: NewPet = {
  name: 'athwart aha deserted',
  birthDate: dayjs('2024-11-17T22:57'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
