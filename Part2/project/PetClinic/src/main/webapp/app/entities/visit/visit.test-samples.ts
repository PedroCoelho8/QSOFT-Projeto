import dayjs from 'dayjs/esm';

import { IVisit, NewVisit } from './visit.model';

export const sampleWithRequiredData: IVisit = {
  id: 463,
  description: 'qualified corporation think',
  date: dayjs('2024-11-18T14:58'),
};

export const sampleWithPartialData: IVisit = {
  id: 9416,
  description: 'typewriter breed hospitalization',
  date: dayjs('2024-11-18T11:31'),
};

export const sampleWithFullData: IVisit = {
  id: 23599,
  description: 'swiftly urgently',
  date: dayjs('2024-11-17T22:22'),
};

export const sampleWithNewData: NewVisit = {
  description: 'claw hmph oof',
  date: dayjs('2024-11-18T04:47'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
