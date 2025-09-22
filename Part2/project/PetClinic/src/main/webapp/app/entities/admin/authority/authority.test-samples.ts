import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'cabd309f-a4db-46b4-935c-04b0d9e656ae',
};

export const sampleWithPartialData: IAuthority = {
  name: '42ff345d-66f5-431d-be18-b67a05812936',
};

export const sampleWithFullData: IAuthority = {
  name: '60cfe76b-9f89-41d5-aa17-3c4a78c7d9bf',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
