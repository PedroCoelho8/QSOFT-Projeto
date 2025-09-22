import dayjs from 'dayjs/esm';
import { IPet } from 'app/entities/pet/pet.model';

export interface IVisit {
  id: number;
  description?: string | null;
  date?: dayjs.Dayjs | null;
  pet?: Pick<IPet, 'id'> | null;
}

export type NewVisit = Omit<IVisit, 'id'> & { id: null };
