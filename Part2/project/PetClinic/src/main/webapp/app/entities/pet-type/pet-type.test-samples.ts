import { IPetType, NewPetType } from './pet-type.model';

export const sampleWithRequiredData: IPetType = {
  id: 16794,
  name: 'psst',
};

export const sampleWithPartialData: IPetType = {
  id: 8592,
  name: 'mockingly inasmuch quaintly',
};

export const sampleWithFullData: IPetType = {
  id: 13620,
  name: 'spotless',
};

export const sampleWithNewData: NewPetType = {
  name: 'colour bah',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
