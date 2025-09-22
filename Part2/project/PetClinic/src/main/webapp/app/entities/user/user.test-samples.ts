import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 32311,
  login: 'py_v6Y',
};

export const sampleWithPartialData: IUser = {
  id: 20298,
  login: 'bWliGu@w0Bwff',
};

export const sampleWithFullData: IUser = {
  id: 1290,
  login: 'a&@piM\\Ht0\\Rv\\~g',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
