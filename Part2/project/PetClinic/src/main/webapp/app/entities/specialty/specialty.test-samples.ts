import { ISpecialty, NewSpecialty } from './specialty.model';

export const sampleWithRequiredData: ISpecialty = {
  id: 4311,
  name: 'to long',
};

export const sampleWithPartialData: ISpecialty = {
  id: 2839,
  name: 'circa',
};

export const sampleWithFullData: ISpecialty = {
  id: 13780,
  name: 'once per afore',
};

export const sampleWithNewData: NewSpecialty = {
  name: 'oh',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
