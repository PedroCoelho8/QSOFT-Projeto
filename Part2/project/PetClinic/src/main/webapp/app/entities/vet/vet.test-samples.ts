import { IVet, NewVet } from './vet.model';

export const sampleWithRequiredData: IVet = {
  id: 18181,
  firstName: 'Claudia',
  lastName: 'Hand',
};

export const sampleWithPartialData: IVet = {
  id: 19414,
  firstName: 'Samanta',
  lastName: 'Crona',
};

export const sampleWithFullData: IVet = {
  id: 30945,
  firstName: 'Miracle',
  lastName: 'Schultz',
};

export const sampleWithNewData: NewVet = {
  firstName: 'Jadyn',
  lastName: 'Glover',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
